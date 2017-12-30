package com.hvitalii.thetanksgame;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.*;
import com.hvitalii.thetanksgame.Model.Bonus;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
import com.hvitalii.thetanksgame.View.BonusView;

import java.awt.*;
import java.util.Date;

public class GameController {

    ResourcesHandler resourcesHandler;
    private TextureAtlas textureAtlas;
    private StageState stageState;
    private Array<Player> players;
    private BonusView bonusView;
    private int playersNumber;
    private int stage;
    private int lastSpawnPosition;
    private int currentMapIndex;
    private long frame;
    private long nextBotSpawnTime;
    private long botFreezeTime;
    private long basesSurroundTime;
    private long exitTime;
    private boolean isTimeToExit;
    private boolean isPreparingSuccess;
    private boolean isDrawBonuses;
    private boolean isShovelActive;

    public GameController(ResourcesHandler resourcesHandler, int playersNumber) {
        this.textureAtlas = resourcesHandler.getAssetManager().get(GameConstants.Files.ATLASES_LOCATION + GameConstants.Files.ATLAS_NAME);
        this.resourcesHandler = resourcesHandler;

        bonusView = new BonusView(textureAtlas);

        stageState = new StageState(this, textureAtlas);
        stageState.setBotsRemaining(20 + (10 * (playersNumber - 1)));

        currentMapIndex = 0;
        isPreparingSuccess = setMap();

        isTimeToExit = false;
        isDrawBonuses = false;
        isShovelActive = false;
        exitTime = 0;
        stage = 1;
        frame = 0;
        nextBotSpawnTime = 0;
        botFreezeTime = 0;
        basesSurroundTime = 0;
        lastSpawnPosition = (int)(Math.random() * 2);

        this.playersNumber = playersNumber;
        players = new Array<Player>();
        for (int i = 0; i < playersNumber; i++) {
            addPlayer(i);
        }
    }


    public void update() {
        long time = new Date().getTime();

        if (exitTime < time) {
            if (exitTime == 0) {
                if (!stageState.isEagleAlive() || !hasAlivePlayer()
                        || ((stageState.getBotsRemaining() == 0) && (stageState.getBots().size == 0))) {
                    exitTime = time + 5000;
                }
            } else {
                isTimeToExit = true;
            }
        }

        if ((stageState.getBots().size < 4) && (nextBotSpawnTime < time)) {
            if (stageState.getBotsRemaining() > 0) {
                spawnBot();
                updateBotSpawnTime(time);
                stageState.setBotsRemaining(stageState.getBotsRemaining() - 1);
            }
        }

        frame++;
        if (frame >= 256) {
            frame = 0;
        }

        { // Check bullets collision
            Array<BulletController> bulletsToRemove = new Array<BulletController>();
            for (int i = 0; i < stageState.getBullets().size - 1; i++) {
                for (int j = i + 1; j < stageState.getBullets().size; j++) {
                    if (stageState.getBullets().get(i).getBounds().overlaps(stageState.getBullets().get(j).getBounds())) {
                        bulletsToRemove.add(stageState.getBullets().get(i));
                        bulletsToRemove.add(stageState.getBullets().get(j));
                    }
                }
            }
            for (int i = 0; i < bulletsToRemove.size; i++) {
                bulletsToRemove.get(i).destruct();
            }
        }
        if (stageState.isEagleAlive() && hasAlivePlayer()) {
            for (int i = 0; i < stageState.getPlayers().size; i++) { //Update players
                stageState.getPlayers().get(i).update(frame);
            }
        }
        if (botFreezeTime < time) {
            for (int i = 0; i < stageState.getBots().size; i++) { //Update bots
                stageState.getBots().get(i).update(frame);
            }
        }

        stageState.getBattleField().update(frame); //Update battle field

        for (int i = 0; i < stageState.getBullets().size; i++) { //Update bullets
            stageState.getBullets().get(i).update(frame);
        }


        for (int i = 0; i < stageState.getBonuses().size; i++) { //Update bonuses
            Bonus bonus = stageState.getBonuses().get(i);
            TankController tank = stageState.getBattleField().getTankAt((int)(bonus.x / 8), (int)(bonus.y / 8));
            if (tank == null) {
                tank = stageState.getBattleField().getTankAt((int)(bonus.x / 8) + 1, (int)(bonus.y / 8));
            }
            if (tank == null) {
                tank = stageState.getBattleField().getTankAt((int)(bonus.x / 8), (int)(bonus.y / 8) + 1);
            }
            if (tank == null) {
                tank = stageState.getBattleField().getTankAt((int)(bonus.x / 8) + 1, (int)(bonus.y / 8) + 1);
            }
            if (tank == null) {
                continue;
            }

            if (tank.getType() == Types.USER) {
                PlayerTankController player = (PlayerTankController) tank;
                player.getPlayer().addScore(500);
                switch (bonus.getType()) {

                    case BonusTypes.SHIELD:
                        player.getModel().setShieldActiveTime(time + Time.BONUS_SHIELD);
                        break;

                    case BonusTypes.TIME_STOP:
                        botFreezeTime = time + Time.BONUS_TIME_STOP;
                        break;

                    case BonusTypes.SHOVEL:
                        stageState.getBattleField().surroundBases(TilesTypes.CONCRETE);
                        basesSurroundTime = time + Time.BONUS_SHOVEL;
                        isShovelActive = true;
                        break;

                    case BonusTypes.STAR:
                        player.upgrade();
                        break;

                    case BonusTypes.GRENADE:
                        destructAllBots();
                        break;

                    case BonusTypes.LIFE:
                        player.addLive();
                        break;

                    case BonusTypes.GUN:
                        player.maxUpgrade();
                        break;
                }
                stageState.getBonuses().removeIndex(i);
            }
        }

        if (isShovelActive && (basesSurroundTime < time)) {
            stageState.getBattleField().surroundBases(TilesTypes.BRICK);
            isShovelActive = false;
        }

        if (frame % 16 == 0) {
            isDrawBonuses = !isDrawBonuses;
        }

//        if (frame % 60 == 0) {
//            for (int i = 0; i < players.size; i++) {
//                System.out.print(i + ": ");
//                System.out.println(players.get(i).getScore());
//            }
//        }

    }

    public void draw(SpriteBatch batch) {

        stageState.getBattleField().drawBottomLayers(batch);

        for (int i = 0; i < stageState.getPlayers().size; i++) { //Draw players
            stageState.getPlayers().get(i).draw(batch);
        }

        for (int i = 0; i < stageState.getBots().size; i++) { //Draw bots
            stageState.getBots().get(i).draw(batch);
        }

        for (int i = 0; i < stageState.getBullets().size; i++) { //Draw bullets
            stageState.getBullets().get(i).draw(batch);
        }

        stageState.getBattleField().drawTopLayers(batch);
        stageState.getBattleField().drawUi(batch);

        if (isDrawBonuses) {
            for (int i = 0; i < stageState.getBonuses().size; i++) {
                bonusView.draw(batch ,stageState.getBonuses().get(i));
            }
        }

    }


    public void spawnBullet(BulletController bullet) {
        stageState.getBullets().add(bullet);
    }

    public void spawnRandomBonus() {
        int type = ObjectsConstants.BONUS_ARRAY[(int)(Math.random() * 8.99)];
        spawnBonus(type);
    }

    public void spawnBonus(int type) {
        Point position = stageState.getBattleField().getRandomFreePosition();
        stageState.getBonuses().add(new Bonus(position.x * 8, position.y * 8, type));
    }

    public void destructBullet(BulletController bullet) {
        stageState.getBullets().removeIndex(stageState.getBullets().indexOf(bullet, true));
    }

    public void destructTank(TankController tank, BulletController bullet) {
        if (tank.getType() == Types.USER) {
            PlayerTankModel tankModel = (PlayerTankModel)tank.getModel();
            int lives = tankModel.getLivesAmount();
            if ( lives > 0) {
                lives--;
                tankModel.setLivesAmount(lives);
                stageState.getPlayers().removeIndex(stageState.getPlayers().indexOf(tank, true));
                respawnPlayer(((PlayerTankController)tank).getPlayer());
                return;
            }
            ((PlayerTankController)tank).getPlayer().kill();
            stageState.getPlayers().removeIndex(stageState.getPlayers().indexOf(tank, true));
            return;
        }
        int botType = ((BotTankController)tank).getBotType();
        ((PlayerTankController)bullet.getOwner()).getPlayer().addDestroyedBot(botType);
        stageState.getBots().removeIndex(stageState.getBots().indexOf(tank, true));

        long time = new Date().getTime();
        if (nextBotSpawnTime < time) {
            updateBotSpawnTime(time);
        }
    }

    public void destructAllBots() {
        stageState.getBots().clear();

        long time = new Date().getTime();
        if (nextBotSpawnTime < time) {
            updateBotSpawnTime(time);
        }
    }

    public boolean hasAlivePlayer() {
        for (int i = 0; i < players.size; i++) {
            if (players.get(i).isAlive()) {
                return true;
            }
        }
        return false;
    }

    public void prepareNextStage(boolean choseRandomMap) {
        stageState = new StageState(this, textureAtlas);
        stageState.setBotsRemaining((playersNumber < 3) ? 20 : (playersNumber * 10));

        int allMapsNumber = resourcesHandler.getInternalMaps().length
                + resourcesHandler.getExternalMaps().length;

        if (choseRandomMap) {
            currentMapIndex = (int)(Math.random() * (allMapsNumber - 1));
        } else {
            if (currentMapIndex >= allMapsNumber) {
                currentMapIndex = 0;
            } else {
                currentMapIndex++;
            }
        }
        isPreparingSuccess = setMap();

        if (!isPreparingSuccess) {
            currentMapIndex = 0;
            isPreparingSuccess = setMap();
        }

        isTimeToExit = false;
        exitTime = 0;
        stage++;
        frame = 0;
        nextBotSpawnTime = 0;
        lastSpawnPosition = (int)(Math.random() * 2);

        preparePlayers();
    }



    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public GameFieldController getBattleField() {
        return stageState.getBattleField();
    }

    public Array<BulletController> getBullets() {
        return stageState.getBullets();
    }

    public Array<TankController> getBotsTanks() {
        return stageState.getBots();
    }

    public Array<TankController> getPlayersTanks() {
        return stageState.getPlayers();
    }

    public Array<Player> getPlayers() {
        return players;
    }

    public StageState getStageState() {
        return stageState;
    }

    public int getStage() {
        return stage;
    }

    public long getFrame() {
        return frame;
    }

    private boolean setMap() {
        FileHandle map;
        int internalMapsNumber = resourcesHandler.getInternalMaps().length;
        if (currentMapIndex < internalMapsNumber) {
            try {
                map = resourcesHandler.getInternalMaps()[currentMapIndex];
                stageState.setMap(map);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                map = resourcesHandler.getExternalMaps()[currentMapIndex - internalMapsNumber];
                stageState.setMap(map);
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private void addPlayer(int playerNumber){
        Player player = new Player(this, textureAtlas, playerNumber);
        players.add(player);
        stageState.getPlayers().add(player.getController());
    }

    private void preparePlayers() {
        for (Player player: players) {
            player.prepare(GameConstants.PLAYERS_SPAWN_POSITIONS[player.getNumber()]);
            if (player.isAlive()) {
                stageState.getPlayers().add(player.getController());
            }
        }
    }

    private void respawnPlayer(Player player) {
        player.reset(GameConstants.PLAYERS_SPAWN_POSITIONS[player.getNumber()]);
        stageState.getPlayers().add(player.getController());
    }

    private void spawnBot(){
        int botType = (int)(Math.random() * 128) / 32 ;
        boolean isBonusCarrier = false;
        switch (stageState.getBotsRemaining()) {
            case 3:
            case 10:
            case 17:
            case 23:
            case 30:
            case 37:
                isBonusCarrier = true;
                break;
        }
        stageState.getBots().add(new BotTankController(
                this,
                GameConstants.BOTS_SPAWN_POSITIONS[nextSpawnPosition()],
                textureAtlas,
                botType,
                isBonusCarrier
        ));
    }

    private int nextSpawnPosition() {
        if (lastSpawnPosition == 2) {
            lastSpawnPosition = 0;
        } else {
            lastSpawnPosition++;
        }
        return lastSpawnPosition;

    }

    private void updateBotSpawnTime(long time) {
        nextBotSpawnTime = time + (long) ((3.17 - stage * 0.07 - (players.size - 1) * 0.34) * 1000);
    }

    public boolean isTimeToExit() {
        return isTimeToExit;
    }

    public boolean isPreparingSuccess() {
        return isPreparingSuccess;
    }
}
