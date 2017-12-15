package com.hvitalii.thetanksgame.Model;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class PlayerTankModel extends com.hvitalii.thetanksgame.Model.Tank {

    private int playerNumber;
    private int level;
    private int livesAmount;


    public PlayerTankModel(int playerNumber) {
        this(playerNumber, Levels.FIRST);
    }

    public PlayerTankModel(int playerNumber, int level) {
        super(Direction.UP, Speed.NORMAL);
        this.playerNumber = playerNumber;
        this.level = level;
        livesAmount = 2;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLivesAmount() {
        return livesAmount;
    }

    public void setLivesAmount(int livesAmount) {
        this.livesAmount = livesAmount;
    }
}
