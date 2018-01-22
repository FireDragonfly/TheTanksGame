package com.hvitalii.thetanksgame.Constants;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

import java.awt.*;

public class GameConstants {

    public static final long FIRE_RATE = 100;

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

    public static final String[] LETTERS_REGIONS_NAMES = {
            "letter_a",
            "letter_e",
            "letter_g",
            "letter_m",
            "letter_o",
            "letter_p",
            "letter_r",
            "letter_s",
            "letter_t",
            "letter_u",
            "letter_v"
    };

    public static Rectangle[] PLAYERS_SPAWN_POSITIONS = {
            new Rectangle(Resolution.TILE_SIZE * 2 * 5, Resolution.TILE_SIZE, Size.TANK, Size.TANK),
            new Rectangle(Resolution.TILE_SIZE * 2 * 9, 8, Size.TANK, Size.TANK),
            new Rectangle(Resolution.TILE_SIZE * 2 * 4, 8, Size.TANK, Size.TANK),
            new Rectangle(Resolution.TILE_SIZE * 2 * 10, 8, Size.TANK, Size.TANK)
    };
    public static Rectangle[] BOTS_SPAWN_POSITIONS = {
            new Rectangle(Resolution.SCREEN_HEIGHT - (ObjectsConstants.Size.TILE * 2 + (Size.BLOCK * 12)),
                    Resolution.SCREEN_HEIGHT - Size.TILE * 3,
                    Size.TANK, Size.TANK),
            new Rectangle(Resolution.SCREEN_HEIGHT - (Size.TILE * 2 + (Size.BLOCK * 6)),
                    Resolution.SCREEN_HEIGHT - Size.TILE * 3,
                    Size.TANK, Size.TANK),
            new Rectangle(Resolution.SCREEN_HEIGHT - (Size.TILE * 2),
                    Resolution.SCREEN_HEIGHT - Size.TILE * 3,
                    Size.TANK, Size.TANK)

    };

    public static String getLetterRegionName(char letter) {
        switch (letter) {
            case 'a':
            case 'A':
                return LETTERS_REGIONS_NAMES[0];
            case 'e':
            case 'E':
                return LETTERS_REGIONS_NAMES[1];
            case 'g':
            case 'G':
                return LETTERS_REGIONS_NAMES[2];
            case 'm':
            case 'M':
                return LETTERS_REGIONS_NAMES[3];
            case 'o':
            case 'O':
                return LETTERS_REGIONS_NAMES[4];
            case 'p':
            case 'P':
                return LETTERS_REGIONS_NAMES[5];
            case 'r':
            case 'R':
                return LETTERS_REGIONS_NAMES[6];
            case 's':
            case 'S':
                return LETTERS_REGIONS_NAMES[7];
            case 't':
            case 'T':
                return LETTERS_REGIONS_NAMES[8];
            case 'u':
            case 'U':
                return LETTERS_REGIONS_NAMES[9];
            case 'v':
            case 'V':
                return LETTERS_REGIONS_NAMES[10];
            default:
                return LETTERS_REGIONS_NAMES[0];
        }
    }

    public static class Files {
        public static final String RESOURCES_LOCATION = "resources/";
        public static final String ATLASES_LOCATION = RESOURCES_LOCATION + "atlases/";
        public static final String FONTS_LOCATION = RESOURCES_LOCATION + "fonts/";
        public static final String LOCAL_MAPS_LOCATION = RESOURCES_LOCATION + "maps/";
        public static final String EXTERNAL_FOLDER_LOCATION = "The Tanks Game/";
        public static final String EXTERNAL_MAPS_LOCATION = EXTERNAL_FOLDER_LOCATION + "maps/";
        public static final String MAP_SUFFIX = ".ttgm";
        public static final String ATLAS_NAME = "atlas.atlas";
        public static final String DEBUGGING_ATLAS_NAME = "debugging_atlas.atlas";

        public static final String[] MAPS_NAMES = {
                "stage_1" + MAP_SUFFIX,
                "stage_2" + MAP_SUFFIX,
                "stage_3" + MAP_SUFFIX,
                "stage_4" + MAP_SUFFIX,
                "stage_5" + MAP_SUFFIX
        };
        public static final int MAPS_COUNT = MAPS_NAMES.length;
    }

    public static class Resolution {
        public static final int FIELD_WIDTH = 32;
        public static final int FIELD_HEIGHT = 28;
        public static final Point BATTLE_FIELD_LEFT_BOTTOM_POINT = new Point(2, 1);
        public static final Point BATTLE_FIELD_RIGHT_TOP_POINT = new Point(FIELD_WIDTH - 5, FIELD_HEIGHT - 2);
        public static final float TILE_SIZE = 8;
        public static final float SCREEN_WIDTH = FIELD_WIDTH * TILE_SIZE;
        public static final float SCREEN_HEIGHT = FIELD_HEIGHT * TILE_SIZE;
    }

    public static class Time {
        public static final long BONUS_SHIELD = 10000;
        public static final long BONUS_TIME_STOP = 10000;
        public static final long BONUS_SHOVEL = 20000;
    }

    public static class Colors {

        public static final Color[] PLAYERS_COLORS = {
                new Color(1, 0.732f, 0.156f, 1),
                new Color(0, 0.825f, 0.285f, 1),
                new Color(0.30f, 0.70f, 1, 1),
                new Color(1f, 0.25f, 0.3f, 1)
        };

        public static final Color BONUSED_TANK = new Color(1,0.1f,0.5f,1);

        public static final Color[] ARMOUR_COLORS = {
                new Color(1, 1, 1, 1),
                new Color(1, 1, 0.7f, 1),
                new Color(0.5f, 1, 0.7f, 1),
                new Color(1, 0.7f, 0.5f, 1)
        };
    }

    public static class UiColors {
        public static final Color TTG_RED = new Color(0xD82800FF);
        public static final Color TTG_GREY = new Color(0x636363FF);
        public static final Color TTG_LIGHT_GREY = new Color(0.65f, 0.65f, 0.65f, 1);
        public static final Color TTG_ORANGE = new Color(0xFC9838FF);
    }

    public static class ControlKeys {

        public static final int KEY_UP = 0;
        public static final int KEY_DOWN = 1;
        public static final int KEY_LEFT = 2;
        public static final int KEY_RIGHT = 3;
        public static final int KEY_FIRE = 4;

        public static final int[][] DEFAULT_PLAYER_KEYS = {
                {
                    Input.Keys.W,
                    Input.Keys.S,
                    Input.Keys.A,
                    Input.Keys.D,
                    Input.Keys.V
                },
                {
                    Input.Keys.UP,
                    Input.Keys.DOWN,
                    Input.Keys.LEFT,
                    Input.Keys.RIGHT,
                    Input.Keys.NUMPAD_0
                },
                {
                    Input.Keys.T,
                    Input.Keys.G,
                    Input.Keys.F,
                    Input.Keys.H,
                    Input.Keys.CONTROL_LEFT
                },
                {
                    Input.Keys.NUMPAD_8,
                    Input.Keys.NUMPAD_5,
                    Input.Keys.NUMPAD_4,
                    Input.Keys.NUMPAD_6,
                    Input.Keys.CONTROL_RIGHT
                },
        };
    }
}
