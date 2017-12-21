package com.hvitalii.thetanksgame;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.PlayerTankController;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;

public class Player {

    private PlayerTankController controller;
    private GameController game;
    private int[] gameBotsDestroyed;
    private int [] stageBotsDestroyed;
    private int score;
    private int playerLevel;
    private int playerNumber;
    private  boolean isAlive;

    public Player(GameController game, TextureAtlas textureAtlas, int playerNumber) {
        this.game = game;
        this.playerNumber = playerNumber;
        gameBotsDestroyed = new int[BotTypes.COUNT];
        stageBotsDestroyed = new int[BotTypes.COUNT];
        controller = new PlayerTankController(this, game, GameConstants.PLAYERS_SPAWN_POSITIONS[playerNumber], textureAtlas, playerNumber);
        score = 0;
        playerLevel = ((PlayerTankModel)controller.getModel()).getLevel();
        isAlive = true;
    }

    public PlayerTankController getController() {
        return controller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void addDestroyedBot(int botType) {
        if ((botType >= 0) && (botType < BotTypes.COUNT)) {
            gameBotsDestroyed[botType]++;
            stageBotsDestroyed[botType]++;
            score += 100 + 100 * botType;
        }
    }

    public void setControlKeys(int[] set) {
        controller.setControlKeys(set);
    }

    public void killed() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
