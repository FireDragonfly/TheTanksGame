package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants;
import com.hvitalii.thetanksgame.Controller.TankController;

import java.awt.*;
import java.util.Scanner;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.TilesTypes;

public class GameFieldModel {

    private int height;
    private int width;
    private byte[][] bottomBlocksLayer;
    private byte[][] topBlocksLayer;
    private byte[][] borderLayer;
    private TankController[][] tanksLayer;
    private byte[][] uiLayer;

    private Array<Point> bases;

    public GameFieldModel() {
        height = Resolution.FIELD_HEIGHT;
        width = Resolution.FIELD_WIDTH;
        bottomBlocksLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        uiLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
        bases = new Array<Point>();
//        bulletLayer = new BulletController[height][width];
        initMap();
        loadBorder();
    }

    public GameFieldModel(FileHandle fileHandle){
        this();
        loadMap(fileHandle);
    }


    public void loadMap(FileHandle fileHandle) {
        Scanner sc = new Scanner(fileHandle.read());
        String name;
        byte block;
        Array<Point> eaglePositions = new Array<Point>();
        boolean eagleExist = false;
        for (int i = height - 2; i > 0; i--) {
            if (!sc.hasNext()){
                break;
            }
            for (int j = 2; j < width - 4; j++) {
                if (sc.hasNext()){
                    name = sc.next();
                } else {
                    break;
                }
                block = getBlockIdFromName(name);
                switch (block) {
                    case TilesTypes.NULL:
                        break;
                    case TilesTypes.BORDER:
                        borderLayer[i][j] = block;
                        break;
                    case TilesTypes.BRICK:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_BRICK:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.CONCRETE:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.WATER_1:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.ICE:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.GRASS:
                        topBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_0_0:
                        bases.add(new Point(j, i));
                        eagleExist = true;
                        break;
                }
            }
        }
        if (eagleExist) {
            for (int i = 0; i < bases.size; i++) {
                drawBase(bases.get(i).x, bases.get(i).y);
            }
        } else {
            drawBase(ObjectsConstants.DEFAULT_EAGLE_POSITION.x,
                    ObjectsConstants.DEFAULT_EAGLE_POSITION.y);
            bases.add(new Point(ObjectsConstants.DEFAULT_EAGLE_POSITION));
//            surroundBase(TilesTypes.BRICK, ObjectsConstants.DEFAULT_EAGLE_POSITION);
        }
    }

    public void set(byte type, Point position) {
        set(type, position.x, position.y);
    }

    public void set(byte type, int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return;
        }
        switch (type) {
            case TilesTypes.NULL:
                borderLayer[y][x] = type;
                bottomBlocksLayer[y][x] = type;
                topBlocksLayer[y][x] = type;
                break;
            case TilesTypes.BORDER:
                borderLayer[y][x] = type;
                break;
            case TilesTypes.BRICK:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.DESTROYED_BRICK:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.CONCRETE:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.WATER_1:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.WATER_2:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.ICE:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.GRASS:
                topBlocksLayer[y][x] = type;
                break;
            case TilesTypes.EAGLE_0_0:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.EAGLE_1_0:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.EAGLE_0_1:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.EAGLE_1_1:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.DESTROYED_EAGLE_0_0:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.DESTROYED_EAGLE_1_0:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.DESTROYED_EAGLE_0_1:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.DESTROYED_EAGLE_1_1:
                bottomBlocksLayer[y][x] = type;
                break;
            case TilesTypes.BOT_IMG:
                uiLayer[y][x] = type;
                break;
            case TilesTypes.PLAYER_IMG:
                uiLayer[y][x] = type;
                break;
//                    case TilesTypes.BOT_SPAWN:
//                        permeableBlocksLayer[i][j] = block;
//                        break;
//                    case TilesTypes.PLAYER_1_SPAWN:
//                        permeableBlocksLayer[i][j] = block;
//                        break;
//                    case TilesTypes.PLAYER_2_SPAWN:
//                        permeableBlocksLayer[i][j] = block;
//                        break;
        }
    }

    public void reset(byte type, int x, int y){
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return;
        }
        borderLayer[y][x] = TilesTypes.NULL;
        bottomBlocksLayer[y][x] = TilesTypes.NULL;
        topBlocksLayer[y][x] = TilesTypes.NULL;
        set(type, x, y);
    }

    public void reset() {
        bottomBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        uiLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
        loadBorder();
    }

    public void clear() {
        clearVisibleLayers();
        clearTankLayer();
        borderLayer = new byte[height][width];
        loadBorder();
    }

    public void clearTankLayer() {
        for (int i = 0; i < tanksLayer.length; i++) {
            for (int j = 0; j < tanksLayer[i].length; j++) {
                tanksLayer[i][j] = null;
            }
        }
    }

    public void clearUiLayer() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                uiLayer[i][j] = 0;
            }
        }
    }

    public void clearVisibleLayers() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                topBlocksLayer[i][j] = 0;
                bottomBlocksLayer[i][j] = 0;
            }
        }
    }

    public void loadBorder() {
        int lastRow = borderLayer.length - 1;
        int lastColumn = borderLayer[0].length - 1;
        for (int i = 0; i < borderLayer[0].length; i++) {
            borderLayer[0][i] = TilesTypes.BORDER;
            borderLayer[lastRow][i] = TilesTypes.BORDER;
        }
        for (int i = 1; i < borderLayer.length - 1; i++) {
            borderLayer[i][0] = TilesTypes.BORDER;
            borderLayer[i][1] = TilesTypes.BORDER;
            for (int j = lastColumn - 3; j <= lastColumn; j++){
                borderLayer[i][j] = TilesTypes.BORDER;
            }

        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public byte get(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return TilesTypes.NULL;
        }
        if (bottomBlocksLayer[y][x] != 0) {

            return bottomBlocksLayer[y][x];

        } else if (borderLayer[y][x] != 0) {

            return borderLayer[y][x];

        } else if (topBlocksLayer[y][x] != 0) {

            return topBlocksLayer[y][x];

        } else  {

            return TilesTypes.NULL;

        }
    }

    public TankController getTank(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return null;
        }
        return tanksLayer[y][x];
    }

    public boolean hasTankAt(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return false;
        }
        return (tanksLayer[y][x] != null);
    }

    public boolean hasTankAt(TankModel tank, int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return false;
        }
        return ((tanksLayer[y][x] != null) && (tank != tanksLayer[y][x].getModel()));
    }

    public boolean hasImpermeableTileAt(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return true;
        }
        if (((bottomBlocksLayer[y][x] == 0) && (borderLayer[y][x] == 0))
            || ((bottomBlocksLayer[y][x] == TilesTypes.ICE))) {
            return false;
        }
        return true;
    }

    public boolean hasIndestructibleTileAt(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return true;
        }
        if (((bottomBlocksLayer[y][x] == TilesTypes.CONCRETE)
                || (bottomBlocksLayer[y][x] == TilesTypes.WATER_1)
                || (bottomBlocksLayer[y][x] == TilesTypes.WATER_2)
                || (!isNoBaseAt(x, y))
                || (borderLayer[y][x] != 0))) {
            return true;
        }
        return false;
    }

    public byte getImpermeableForBulletTileAt(int x, int y) {
        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
            return TilesTypes.NULL;
        }
        if (bottomBlocksLayer[y][x] != 0) {

            return bottomBlocksLayer[y][x];

        } else if (borderLayer[y][x] != 0) {

            return borderLayer[y][x];

        } else  {

            return TilesTypes.NULL;

        }
    }

    public Array<Point> getBases() {
        return bases;
    }

    public byte[][] getBottomBlocksLayer() {
        return bottomBlocksLayer;
    }

    public TankController[][] getTanksLayer() {
        return tanksLayer;
    }

    public byte[][] getBorderLayer() {
        return borderLayer;
    }

    public byte[][] getUiLayer() {
        return uiLayer;
    }

    public byte[][] getCompositeLayer() {
        byte[][] layer = new byte[width][height];
        for (int i = 1; i <= height - 2; i++) {
            for (int j = 2; j <= width - 5; j++) {
                if (bottomBlocksLayer[i][j] != TilesTypes.NULL) {
                    layer[i][j] = bottomBlocksLayer[i][j];
                } else if (topBlocksLayer[i][j] != TilesTypes.NULL) {
                    layer[i][j] = topBlocksLayer[i][j];
                } else {
                    layer[i][j] = TilesTypes.NULL;
                }
            }
        }
        return layer;
    }

    public byte[][] getMap() {
        byte[][] layer = new byte[width-6][height-2];
        for (int i = 1, i2 = 0; i <= height - 2; i++, i2++) {
            for (int j = 2, j2 = 0; j <= width - 5; j++, j2++) {
                if (bottomBlocksLayer[i][j] != TilesTypes.NULL) {
                    layer[i2][j2] = bottomBlocksLayer[i][j];
                } else if (topBlocksLayer[i][j] != TilesTypes.NULL) {
                    layer[i2][j2] = topBlocksLayer[i][j];
                } else {
                    layer[i2][j2] = TilesTypes.NULL;
                }
            }
        }
        return layer;
    }

    public boolean hasAliveBase() {
        for (int i = 1; i <= height - 2; i++) {
            for (int j = 2; j <= width - 5; j++) {
                if (bottomBlocksLayer[i][j] == TilesTypes.EAGLE_0_0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void drawBase(int x, int y) {
        if (x >= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x) {
            x = Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x - 1;
        } else if (x < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x) {
            x = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x;
        }
        if (y >= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y) {
            y = Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y - 1;
        } else if (y < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.y) {
            y = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.y;
        }
        set(TilesTypes.EAGLE_0_0, x, y);
        set(TilesTypes.EAGLE_1_0, x + 1, y);
        set(TilesTypes.EAGLE_0_1, x, y + 1);
        set(TilesTypes.EAGLE_1_1, x + 1, y + 1);
    }

    public void surroundBase(byte blockType, Point baseBottomLeft) {
        surroundBase(blockType, baseBottomLeft.x, baseBottomLeft.y);
    }

    public void surroundBase(byte blockType, int baseBottomLeftX, int baseBottomLeftY) {
        int x = baseBottomLeftX - 1;
        int y = baseBottomLeftY - 1;
        for (int i = 0; i < 4; i++){
            int currentX = x + i;
            if (isNoBaseAt(currentX, y) && isInBattleField(currentX, y)) {
                set(blockType,currentX, y);
            }
            if (isNoBaseAt(currentX, y + 3) && isInBattleField(currentX, y + 3)) {
                set(blockType,currentX, y + 3);
            }
        }

        for (int i = 1; i < 3; i++){
            int currentY = y + i;
            if (isNoBaseAt(x, currentY) && isInBattleField(x, currentY)) {
                set(blockType, x, currentY);
            }
            if (isNoBaseAt(x + 3, currentY) && isInBattleField(x + 3, currentY)) {
                set(blockType, x + 3, currentY);
            }
        }
    }

    public static byte getBlockIdFromName(String name) {
        name = name.toLowerCase();

        if ("br".equals(name) || "border".equals(name) || "1".equals(name)) {
            return TilesTypes.BORDER;

        } else if ("b".equals(name) || "brick".equals(name) || "2".equals(name)) {
            return TilesTypes.BRICK;

        } else if ("db".equals(name) || "dbrick".equals(name) || "3".equals(name)) {
            return TilesTypes.DESTROYED_BRICK;

        } else if ("c".equals(name) || "concrete".equals(name) || "4".equals(name)) {
            return TilesTypes.CONCRETE;

        } else if ("w".equals(name) || "water".equals(name) || "5".equals(name)) {
            return TilesTypes.WATER_1;

        } else if ("i".equals(name) || "ice".equals(name) || "7".equals(name)) {
            return TilesTypes.ICE;

        } else if ("g".equals(name) || "grass".equals(name) || "8".equals(name)) {
            return TilesTypes.GRASS;

        } else if ("e".equals(name) || "eagle".equals(name) || "9".equals(name)) {
            return TilesTypes.EAGLE_0_0;

        } else {
            return TilesTypes.NULL;
        }
    }

    public static boolean isInBattleField(int x, int y) {
        if ((x > Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x)
            ||(x < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x)
            ||(y > Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y)
            ||(y < Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.y)) {
            return false;
        }
        return true;
    }

    private boolean isNoBaseAt(int x, int y) {
        byte block = get(x, y);
        switch (block) {
            case TilesTypes.EAGLE_0_0:
            case TilesTypes.EAGLE_1_0:
            case TilesTypes.EAGLE_0_1:
            case TilesTypes.EAGLE_1_1:
            case TilesTypes.DESTROYED_EAGLE_0_0:
            case TilesTypes.DESTROYED_EAGLE_1_0:
            case TilesTypes.DESTROYED_EAGLE_0_1:
            case TilesTypes.DESTROYED_EAGLE_1_1:
                return false;
        }
        return true;
    }

    private void initMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bottomBlocksLayer[i][j] = 0;
                borderLayer[i][j] = 0;
                topBlocksLayer[i][j] = 0;
                uiLayer[i][j] = 0;
                tanksLayer[i][j] = null;
//                bulletLayer[i][j] = null;
            }
        }
    }
}

