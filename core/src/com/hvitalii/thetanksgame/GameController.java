package com.hvitalii.thetanksgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.*;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

import java.util.Date;

public class GameController {

    private ResourcesHandler resourcesHandler;
    private TextureAtlas textureAtlas;
    private StageState stageState;
    private Array<Player> players;
    private int playersNumber;
    private int stage;
    private long frame;
    private long nextBotSpawnTime;
    private long exitTime;
    private boolean isTimeToExit;

    public GameController(ResourcesHandler resourcesHandler, int playersNumber) {
        isTimeToExit = false;
        this.resourcesHandler = resourcesHandler;
        textureAtlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.ATLAS_NAME);
//        textureAtlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.DEBUGGING_ATLAS_NAME);
        stageState = new StageState(this, textureAtlas);
        stageState.setMap(resourcesHandler.getMaps()[0]);
        stageState.setBotsRemaining(20);
        players = new Array<Player>();

        stage = 0;
        frame = 0;
        nextBotSpawnTime = 0;

        this.playersNumber = playersNumber;
        for (int i = 0; i < playersNumber; i++) {
            addPlayer(i);
        }

        exitTime = 0;
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
        if (frame == 256) {
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
        if (stageState.isEagleAlive()) {
            for (int i = 0; i < stageState.getPlayers().size; i++) { //Update players
                stageState.getPlayers().get(i).update(frame);
            }
        }
        for (int i = 0; i < stageState.getBots().size; i++) { //Update bots
            stageState.getBots().get(i).update(frame);
        }

        stageState.getBattleField().update(frame); //Update battle field

        for (int i = 0; i < stageState.getBullets().size; i++) { //Update bullets
            stageState.getBullets().get(i).update(frame);
        }

        if (frame % 60 == 0) {
            for (int i = 0; i < players.size; i++) {
                System.out.print(i + ": ");
                System.out.println(players.get(i).getScore());
            }
        }

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

    }


    public void addPlayer(int playerNumber){
        Player player = new Player(this, textureAtlas, playerNumber);
        players.add(player);
        stageState.getPlayers().add(player.getController());
    }

    public void respawnPlayer(PlayerTankController player) {
        player.reset(GameConstants.PLAYERS_SPAWN_POSITIONS[((PlayerTankModel)player.getModel()).getPlayerNumber()]);
    }

    public void spawnBullet(BulletController bullet) {
        stageState.getBullets().add(bullet);
    }

    public void spawnBot(){
        int spawnPosition = (int)(Math.random() * 75) / 32;
        int botType = (int)(Math.random() * 128) / 32 ;
        stageState.getBots().add(new BotTankController(this, GameConstants.BOTS_SPAWN_POSITIONS[spawnPosition], textureAtlas, botType, -1));
    }

    public void spawnBonus(int bonusType) {
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                respawnPlayer((PlayerTankController) tank);
                return;
            }
            ((PlayerTankController)tank).getPlayer().killed();
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

    public boolean hasAlivePlayer() {
        for (int i = 0; i < players.size; i++) {
            if (players.get(i).isAlive()) {
                return true;
            }
        }
        return false;
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

    public long getFrame() {
        return frame;
    }

    private void updateBotSpawnTime(long time) {
        nextBotSpawnTime = time + Math.abs(3500 - (50 * stage) * playersNumber);
    }

    public boolean isTimeToExit() {
        return isTimeToExit;
    }
}
