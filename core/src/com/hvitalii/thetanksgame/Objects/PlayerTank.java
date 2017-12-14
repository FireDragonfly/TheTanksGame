package com.hvitalii.thetanksgame.Objects;

public class PlayerTank extends Tank {

    private int playerNumber;
    private int level;

    private int livesAmount;


    public PlayerTank(int playerNumber) {
        this(playerNumber, Levels.FIRST);
    }

    public PlayerTank(int playerNumber, int level) {
        super(Types.USER, Direction.UP, Speed.NORMAL);
        this.playerNumber = playerNumber;
        this.level = level;
        livesAmount = 2;
    }

    @Override
    public int[] getVisualCondition() {
        int[] condition = new int[5];
        condition[0] = getType();
        condition[1] = playerNumber;
        condition[2] = level;
        condition[3] = getDirection();
        condition[4] = getAnimationFrame();
        return condition;
    }

    @Override
    public void move(int direction) {
        switchAnimationFrame();
    }
}
