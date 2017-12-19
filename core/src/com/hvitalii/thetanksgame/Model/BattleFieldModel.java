package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.files.FileHandle;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Controller.BulletController;
import com.hvitalii.thetanksgame.Controller.TankController;

import java.util.Scanner;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.TilesTypes;

public class BattleFieldModel {

    private int height;
    private int width;
    private byte[][] bottomBlocksLayer;
    private byte[][] topBlocksLayer;
    private byte[][] borderLayer;
    private TankController[][] tanksLayer;
//    private BulletController[][] bulletLayer;

    public BattleFieldModel() {
        height = Resolution.FIELD_HEIGHT;
        width = Resolution.FIELD_WIDTH;
        bottomBlocksLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
//        bulletLayer = new BulletController[height][width];
//        initMap();
        loadBorder();
    }

    public BattleFieldModel(FileHandle fileHandle){
        this();
        loadMap(fileHandle);
    }


    public void loadMap(FileHandle fileHandle) {
        Scanner sc = new Scanner(fileHandle.read());
        byte block;
        for (int i = height - 2; i > 0; i--) {
            for (int j = 2; j < width - 4; j++) {
                block = sc.nextByte();
                switch (block) {
                    case TilesTypes.NULL:
                        break;
                    case TilesTypes.GRAY:
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
                    case TilesTypes.WATER_2:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.ICE:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.GRASS:
                        topBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_0_0:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_1_0:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_0_1:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_1_1:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_0:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_0:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_1:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_1:
                        bottomBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.BOT_IMG:
                        borderLayer[i][j] = block;
                        break;
                    case TilesTypes.PLAYER_IMG:
                        borderLayer[i][j] = block;
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
                break;
            case TilesTypes.GRAY:
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
                borderLayer[y][x] = type;
                break;
            case TilesTypes.PLAYER_IMG:
                borderLayer[y][x] = type;
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
            borderLayer[0][i] = TilesTypes.GRAY;
            borderLayer[lastRow][i] = TilesTypes.GRAY;
        }
        for (int i = 1; i < borderLayer.length - 1; i++) {
            borderLayer[i][0] = TilesTypes.GRAY;
            borderLayer[i][1] = TilesTypes.GRAY;
            for (int j = lastColumn - 3; j <= lastColumn; j++){
                borderLayer[i][j] = TilesTypes.GRAY;
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
                tanksLayer[i][j] = null;
//                bulletLayer[i][j] = null;
            }
        }
    }
}

