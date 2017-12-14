package com.hvitalii.thetanksgame.Objects;


import com.badlogic.gdx.math.Rectangle;

abstract class GameObject extends Rectangle {

    private int type;


    public GameObject(int type) {
        super();
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public abstract int[] getVisualCondition();

    public static class Types {
        public static final int BLOCK = 0;
        public static final int BOT = 1;
        public static final int USER = 2;
    }

    public static class Levels {
        public static final int FIRST = 0;
        public static final int SECOND = 1;
        public static final int THIRD = 2;
        public static final int FOURTH = 3;
    }

    public static class BotTypes {
        public static final int SMALL = 0;
        public static final int APC = 1;
        public static final int FAST = 2;
        public static final int HEAVY = 3;
    }

    public static class BotState {
        public static final int BONUS = 1;
        public static final int DEFAULT = 0;
    }

    public static class Speed {
        public static final float FAST_BULLET_SPEED = 4F;
        public static final float NORMAL_BULLET_SPEED = 2F;
        public static final float FAST = 1F;
        public static final float NORMAL = 0.5F;
        public static final float SLOW = 0.334F;
        public static final float VERY_SLOW = 0.25F;
    }

    public static class Direction {
        public static final int UP = 0;
        public static final int LEFT = 1;
        public static final int DOWN = 2;
        public static final int RIGHT = 3;
    }

    public static class BulletsTypes {
        public static final int NORMAL = 0;
        public static final int FAST = 1;
        public static final int AP = 2;
    }
}
