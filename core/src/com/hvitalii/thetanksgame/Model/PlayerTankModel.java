package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Constants.PlayerLevelsAtributes;

public class PlayerTankModel extends TankModel {

    private int playerNumber;
    private int level;
    private int livesAmount;


    public PlayerTankModel(Rectangle rectangle, int playerNumber) {
        this(rectangle, playerNumber, Levels.FIRST);
    }

    public PlayerTankModel(Rectangle rectangle, int playerNumber, int level) {
        super(rectangle, Direction.UP, Speed.NORMAL);
        this.playerNumber = playerNumber;
        setLevel(level);
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
        if ((level >= Levels.FIRST) && (level <= Levels.FOURTH)) {
            this.level = level;
        } else {
            this.level = Levels.FIRST;
        }
        PlayerLevelsAtributes.levels[level].loadAttributes(this);
        setBulletsAmount(getBulletsMax());
    }

    public int getLivesAmount() {
        return livesAmount;
    }

    public void setLivesAmount(int livesAmount) {
        this.livesAmount = livesAmount;
    }
}
