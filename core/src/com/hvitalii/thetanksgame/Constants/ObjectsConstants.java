package com.hvitalii.thetanksgame.Constants;


import com.badlogic.gdx.graphics.Color;

public class ObjectsConstants {
//    public static class Types {
//        public static final int BLOCK = 0;
//        public static final int BOT = 1;
//        public static final int USER = 2;
//        public static final int BULLET = 3;
//    }

    public static final String[] PLAYER_TANKS_NAMES = {
            "player_L1",
            "player_L2",
            "player_L3",
            "player_L4"
    };

    public static final String[] BOT_TYPES_NAMES = {
            "bot_small",
            "bot_apc",
            "bot_fast",
            "bot_heavy"
    };

    public static class Direction {
        public static final float UP = 0;
        public static final float LEFT = 90;
        public static final float DOWN = 180;
        public static final float RIGHT = 270;
    }

    public static class Speed {
        public static final float FAST_BULLET_SPEED = 4F;
        public static final float NORMAL_BULLET_SPEED = 2F;
        public static final float FAST = 1F;
        public static final float NORMAL = 0.5F;
        public static final float SLOW = 0.334F;
        public static final float VERY_SLOW = 0.25F;
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

//    public static class BotState {
//        public static final int BONUS = 1;
//        public static final int DEFAULT = 0;
//    }

    public static class BulletsTypes {
        public static final int NORMAL = 0;
        public static final int FAST = 1;
        public static final int AP = 2;
    }

    public static class Colors {
        public static final Color PLAYERS_COLORS[] = {
                new Color(1, 1, 0, 1),
                new Color(0.1f, 1, 0.2f, 1)
        };

        public static final Color BONUSED_TANK = new Color(1,0.1f,0.5f,1);

        public static final Color ARMOUR_COLORS[] = {
                new Color(0, 0.5f, 1, 1),
                new Color(0.5f, 1, 0, 1),
                new Color(1, 0.5f, 0, 1)
        };



    }
}
