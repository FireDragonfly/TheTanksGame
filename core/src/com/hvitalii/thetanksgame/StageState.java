package com.hvitalii.thetanksgame;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Controller.BulletController;
import com.hvitalii.thetanksgame.Controller.GameFieldController;
import com.hvitalii.thetanksgame.Controller.TankController;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

public class StageState {

    private Array<TankController> bots;
    private Array<TankController> players;
    private Array<BulletController> bullets;
    private GameFieldController battleField;
    private FileHandle map;
    private boolean isEagleAlive;
    private int botsRemaining;

    public StageState(GameController gameController, TextureAtlas textureAtlas) {
        battleField = new GameFieldController(gameController,this, textureAtlas);
        bots = new Array<TankController>();
        players = new Array<TankController>();
        bullets = new Array<BulletController>();
        isEagleAlive = true;
    }

    public StageState(GameController gameController, TextureAtlas textureAtlas, FileHandle map) {
        this(gameController, textureAtlas);
        setMap(map);
    }

    public Array<TankController> getBots() {
        return bots;
    }

    public Array<BulletController> getBullets() {
        return bullets;
    }

    public GameFieldController getBattleField() {
        return battleField;
    }

    public Array<TankController> getPlayers() {
        return players;
    }

    public void setMap(FileHandle map) {
        battleField.setMap(map);
        this.map = map;
    }

    public void resetMap() {
        battleField.setMap(map);
    }

    public boolean isEagleAlive() {
        return isEagleAlive;
    }

    public void eagleKilled() {
        isEagleAlive = false;
    }

    public int getBotsRemaining() {
        return botsRemaining;
    }

    public void setBotsRemaining(int botsRemaining) {
        this.botsRemaining = botsRemaining;
    }
}
