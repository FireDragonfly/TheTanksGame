package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.files.FileHandle;
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
//    private BulletController[][] bulletLayer;

    public GameFieldModel() {
        height = Resolution.FIELD_HEIGHT;
        width = Resolution.FIELD_WIDTH;
        bottomBlocksLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        uiLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
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
        Point eaglePosition = new Point();
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
                        eaglePosition = new Point(j, i);
                        eagleExist = true;
                        break;
                }
            }
        }
        if (eagleExist) {
            drawEagle(eaglePosition.x, eaglePosition.y);
        } else {
            drawEagle(ObjectsConstants.DEFAULT_EAGLE_POSITION.x,
                    ObjectsConstants.DEFAULT_EAGLE_POSITION.y);
        }
    }

    public void set(byte type, int x, int y){
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

//    public BulletController getBullet(int x, int y) {
//        if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
//            return null;
//        }
//        return bulletLayer[y][x];
//    }

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
        if ((bottomBlocksLayer[y][x] == 0)
                && (bottomBlocksLayer[y][x] != TilesTypes.ICE)
                && (borderLayer[y][x] == 0)) {
            return false;
        }
        return true;
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

    public byte[][] getBottomBlocksLayer() {
        return bottomBlocksLayer;
    }

    public TankController[][] getTanksLayer() {
        return tanksLayer;
    }

//    public BulletController[][] getBulletLayer() {
//        return bulletLayer;
//    }

    public byte[][] getBorderLayer() {
        return borderLayer;
    }

    public byte[][] getUiLayer() {
        return uiLayer;
    }

    public void clear() {
        bottomBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
//        bulletLayer = new BulletController[height][width];
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

    private byte getBlockIdFromName(String name) {
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

    private void drawEagle(int x, int y) {
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
}

