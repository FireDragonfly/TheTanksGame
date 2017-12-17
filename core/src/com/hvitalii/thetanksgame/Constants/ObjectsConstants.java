package com.hvitalii.thetanksgame.Constants;


import com.badlogic.gdx.graphics.Color;

public class ObjectsConstants {
    public static class Types {
        public static final int BOT = 0;
        public static final int USER = 1;
    }

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

    public static class Size {
        public static final float BULLET = 4;
        public static final float TANK = 16;
        public static final float TILE = 8;
        public static final float BLOCK = 16;
    }

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

    public static class TilesTypes {
        public static final int NULL = 0;
        public static final int GRAY = 1;
        public static final int BRICK = 2;
        public static final int DESTROYED_BRICK = 3;
        public static final int CONCRETE = 4;
        public static final int WATER_1 = 5;
        public static final int WATER_2 = 6;
        public static final int ICE = 7;
        public static final int GRASS = 8;
        public static final int EAGLE_0_0 = 9;
        public static final int EAGLE_1_0 = 10;
        public static final int EAGLE_0_1 = 11;
        public static final int EAGLE_1_1 = 12;
        public static final int DESTROYED_EAGLE_0_0 = 13;
        public static final int DESTROYED_EAGLE_1_0 = 14;
        public static final int DESTROYED_EAGLE_0_1 = 15;
        public static final int DESTROYED_EAGLE_1_1 = 16;
        public static final int BOT_IMG = 17;
        public static final int PLAYER_IMG = 18;
        public static final int BOT_SPAWN = 19;
        public static final int PLAYER_1_SPAWN = 20;
        public static final int PLAYER_2_SPAWN = 21;

    }

    public static class Colors {
        public static final Color PLAYERS_COLORS[] = {
                new Color(1, 1, 0, 1),
                new Color(0.1f, 1, 0.2f, 1)
        };

        public static final Color BONUSED_TANK = new Color(1,0.1f,0.5f,1);

        public static final Color ARMOUR_COLORS[] = {
                new Color(1, 1, 1, 1),
                new Color(0.5f, 1, 0, 1),
                new Color(1, 0.5f, 0, 1)
        };
    }
}
