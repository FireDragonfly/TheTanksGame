package com.hvitalii.thetanksgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.*;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

import java.util.Date;

public class GameController {

    private ResourcesHandler resourcesHandler;
    private Array<TankController> bots;
    private Array<TankController> players;
    private Array<BulletController> bullets;
    private BattleFieldController battleField;
    private TextureAtlas textureAtlas;
    private Rectangle[] playersSpawnPositions;
    private Rectangle[] botsSpawnPositions;
    private int playersNumber;
    private int stage;
    private long frame;
    private long nextBotSpawnTime;

    public GameController(ResourcesHandler resourcesHandler, int playersNumber) {
        this.resourcesHandler = resourcesHandler;
        textureAtlas = resourcesHandler.getAssetManager().get("atlas.atlas");
        //textureAtlas = resourcesHandler.getAssetManager().get("debugging_atlas.atlas");

        this.playersNumber = playersNumber;
        stage = 1;
        frame = 0;
        nextBotSpawnTime = 0;

        playersSpawnPositions = new Rectangle[2];
        playersSpawnPositions[0] = new Rectangle(Resolution.TILE_SIZE * 2 * 5, Resolution.TILE_SIZE, Size.TANK, Size.TANK);
        playersSpawnPositions[1] = new Rectangle(Resolution.TILE_SIZE * 2 * 9, 8, Size.TANK, Size.TANK);

        botsSpawnPositions = new Rectangle[3];
        botsSpawnPositions[0] = new Rectangle(Resolution.SCREEN_HEIGHT - (Size.TILE * 2 + (Size.BLOCK * 12)) , Resolution.SCREEN_HEIGHT - Size.TILE * 3, Size.TANK, Size.TANK);
        botsSpawnPositions[1] = new Rectangle(Resolution.SCREEN_HEIGHT - (Size.TILE * 2 + (Size.BLOCK * 6)) , Resolution.SCREEN_HEIGHT - Size.TILE * 3, Size.TANK, Size.TANK);
        botsSpawnPositions[2] = new Rectangle(Resolution.SCREEN_HEIGHT - (Size.TILE * 2) , Resolution.SCREEN_HEIGHT - Size.TILE * 3, Size.TANK, Size.TANK);


        battleField = new BattleFieldController(this, textureAtlas);
        battleField.setMap(resourcesHandler.getMaps()[0]);
        bots = new Array<TankController>();
        players = new Array<TankController>();
        bullets = new Array<BulletController>();

        spawnPlayer(0);
        if (playersNumber > 0){
            spawnPlayer(1);
        }

    }

    public void update() {
        long time = new Date().getTime();
        if ((bots.size < 4) && (nextBotSpawnTime < time)) {
            spawnBot();
            updateBotSpawnTyme(time);
        }

        frame++;
        if (frame == 256) {
            frame = 0;
        }
        {
            Array<BulletController> bulletsToRemove = new Array<BulletController>();
            for (int i = 0; i < bullets.size - 1; i++) {
                for (int j = i + 1; j < bullets.size; j++) {
                    if (bullets.get(i).getBounds().overlaps(bullets.get(j).getBounds())) {
                        bulletsToRemove.add(bullets.get(i));
                        bulletsToRemove.add(bullets.get(j));
                    }
                }
            }
            for (int i = 0; i < bulletsToRemove.size; i++) {
                bulletsToRemove.get(i).destruct();
            }
        }


        for (int i = 0; i < players.size; i++) { //Update players
            players.get(i).update(frame);
        }
        for (int i = 0; i < bots.size; i++) { //Update bots
            bots.get(i).update(frame);
        }

        battleField.update(frame);

        for (int i = 0; i < bullets.size; i++) { //Update bullets
            bullets.get(i).update(frame);
        }

    }

    public void draw(SpriteBatch batch) {

        battleField.drawBottomLayers(batch);

        for (int i = 0; i < players.size; i++) { //Draw players
            players.get(i).draw(batch);
        }

        for (int i = 0; i < bots.size; i++) { //Draw bots
            bots.get(i).draw(batch);
        }

        for (int i = 0; i < bullets.size; i++) { //Draw bullets
            bullets.get(i).draw(batch);
        }

        battleField.drawTopLayers(batch);

    }

    public void spawnBullet(BulletController bullet) {
        bullets.add(bullet);
    }

    public void spawnPlayer(int playerNumber){
        players.add(new PlayerTankController(this, playersSpawnPositions[playerNumber], textureAtlas, playerNumber, 3));
    }

    public void spawnBot(){
        int spawnPosition = (int)(Math.random() * 75) / 32;
        int botType = (int)(Math.random() * 96) / 32 ;
        bots.add(new BotTankController(this, botsSpawnPositions[spawnPosition], textureAtlas, botType, -1));
    }

    public void respawnPlayer(PlayerTankController player) {
        player.reset(playersSpawnPositions[((PlayerTankModel)player.getModel()).getPlayerNumber()]);
    }

    public void destructBullet(BulletController bullet) {
        bullets.removeIndex(bullets.indexOf(bullet, true));
    }

    public void destructTank(TankController tank) {
        if (tank.getType() == Types.USER) {
            PlayerTankModel tankModel = (PlayerTankModel)tank.getModel();
            int lives = tankModel.getLivesAmount();
            if ( lives > 0) {
                lives--;
                tankModel.setLivesAmount(lives);
                respawnPlayer((PlayerTankController) tank);
                return;
            }
            players.removeIndex(players.indexOf(tank, true));
            return;
        }
        bots.removeIndex(bots.indexOf(tank, true));

        long time = new Date().getTime();
        if (nextBotSpawnTime < time) {
            updateBotSpawnTyme(time);
        }
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public BattleFieldController getBattleField() {
        return battleField;
    }

    public Array<BulletController> getBullets() {
        return bullets;
    }

    public Array<TankController> getBotsTanks() {
        return bots;
    }

    public Array<TankController> getPlayersTanks() {
        return players;
    }

    public long getFrame() {
        return frame;
    }

    private void updateBotSpawnTyme(long time) {
        nextBotSpawnTime = time + Math.abs(3500 - (50 * stage) * playersNumber);
    }
}
