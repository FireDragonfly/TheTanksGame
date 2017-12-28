package com.hvitalii.thetanksgame.Constants;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.awt.*;

public class ObjectsConstants {

    public static final int[] BONUS_ARRAY = {0, 1, 2, 3, 4, 5, 6, 0, 4};

    public static class Types {
        public static final int BOT = 0;
        public static final int USER = 1;
    }

    public static class Size {
        public static final float BULLET = 4;
        public static final float TILE = GameConstants.Resolution.TILE_SIZE;
        public static final float BLOCK = TILE * 2;
        public static final float TANK = BLOCK;
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
        public static final float NORMAL = 0.75F;
        public static final float SLOW = 0.5F;
        public static final float VERY_SLOW = 0.334F;
    }

    public static class Levels {
        public static final int FIRST = 0;
        public static final int SECOND = 1;
        public static final int THIRD = 2;
        public static final int FOURTH = 3;
    }

    public static class BotTypes {
        public static final int COUNT = 4;
        public static final int SMALL = 0;
        public static final int APC = 1;
        public static final int FAST = 2;
        public static final int HEAVY = 3;
    }

    public static class BulletsTypes {
        public static final int NORMAL = 0;
        public static final int FAST = 1;
        public static final int AP = 2;
    }

    public static class BonusTypes {
        public static final int SHIELD = 0;
        public static final int TIME = 1;
        public static final int SHOVEL = 2;
        public static final int STAR = 3;
        public static final int GRENADE = 4;
        public static final int LIFE = 5;
        public static final int GUN = 6;
    }


    public static class TilesTypes {
        public static final byte NULL = 0;
        public static final byte BORDER = 1;
        public static final byte BRICK = 2;
        public static final byte DESTROYED_BRICK = 3;
        public static final byte CONCRETE = 4;
        public static final byte WATER_1 = 5;
        public static final byte WATER_2 = 6;
        public static final byte ICE = 7;
        public static final byte GRASS = 8;
        public static final byte EAGLE_0_0 = 9;
        public static final byte EAGLE_1_0 = 10;
        public static final byte EAGLE_0_1 = 11;
        public static final byte EAGLE_1_1 = 12;
        public static final byte DESTROYED_EAGLE_0_0 = 13;
        public static final byte DESTROYED_EAGLE_1_0 = 14;
        public static final byte DESTROYED_EAGLE_0_1 = 15;
        public static final byte DESTROYED_EAGLE_1_1 = 16;
        public static final byte BOT_IMG = 17;
        public static final byte PLAYER_IMG = 18;

        public static final byte DIGIT_0 = 19;
        public static final byte DIGIT_1 = 20;
        public static final byte DIGIT_2 = 21;
        public static final byte DIGIT_3 = 22;
        public static final byte DIGIT_4 = 23;
        public static final byte DIGIT_5 = 24;
        public static final byte DIGIT_6 = 25;
        public static final byte DIGIT_7 = 26;
        public static final byte DIGIT_8 = 27;
        public static final byte DIGIT_9 = 28;

        public static final byte LETTER_A = 29;
        public static final byte LETTER_E = 30;
        public static final byte LETTER_G = 31;
        public static final byte LETTER_M = 32;
        public static final byte LETTER_O = 33;
        public static final byte LETTER_P = 34;
        public static final byte LETTER_R = 35;
        public static final byte LETTER_S = 36;
        public static final byte LETTER_T = 37;
        public static final byte LETTER_U = 37;
        public static final byte LETTER_V = 39;
    }

    public static int[] intToTiles(int n) {
        if (n > 0) {
            Array<Integer> array = new Array<Integer>();
            while (n > 0) {
                array.add((n % 10) + TilesTypes.DIGIT_0);
                n /= 10;
            }
            array.reverse();
            int[] result = new int[array.size];
            for (int i = 0; i < array.size; i++){
                result[i] = array.get(i).intValue();
            }
            return result;
        } else {
            return new int[] { TilesTypes.DIGIT_0 };
        }

    }

    public static Point DEFAULT_EAGLE_POSITION = new Point(14, 0);
}
