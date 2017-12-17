package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.files.FileHandle;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Controller.TankController;

import java.util.Scanner;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.TilesTypes;

public class BattleFieldModel {

    private int height;
    private int width;
    private byte[][] permeableBlocksLayer;
    private byte[][] impermeableBlocksLayer;
    private byte[][] topBlocksLayer;
    private byte[][] borderLayer;
    private TankController[][] tanksLayer;

    public BattleFieldModel() {
        height = Resolution.FIELD_HEIGHT;
        width = Resolution.FIELD_WIDTH;
        permeableBlocksLayer = new byte[height][width];
        impermeableBlocksLayer = new byte[height][width];
        topBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
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
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_BRICK:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.CONCRETE:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.WATER_1:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.WATER_2:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.ICE:
                        permeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.GRASS:
                        topBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_0_0:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_1_0:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_0_1:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.EAGLE_1_1:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_0:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_0:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_1:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_1:
                        impermeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.BOT_IMG:
                        borderLayer[i][j] = block;
                        break;
                    case TilesTypes.PLAYER_IMG:
                        borderLayer[i][j] = block;
                        break;
                    case TilesTypes.BOT_SPAWN:
                        permeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.PLAYER_1_SPAWN:
                        permeableBlocksLayer[i][j] = block;
                        break;
                    case TilesTypes.PLAYER_2_SPAWN:
                        permeableBlocksLayer[i][j] = block;
                        break;
                }
            }
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
        if (impermeableBlocksLayer[y][x] != 0) {

            return impermeableBlocksLayer[y][x];

        } else if (borderLayer[y][x] != 0) {

            return borderLayer[y][x];

        } else if (permeableBlocksLayer[y][x] != 0) {

            return permeableBlocksLayer[y][x];

        } else if (topBlocksLayer[y][x] != 0) {

            return topBlocksLayer[y][x];

        } else  {

            return TilesTypes.NULL;

        }
    }

    public TankController getTank(int x, int y) {
        return tanksLayer[y][x];
    }

    public boolean hasTankAt(int x, int y) {
        return (tanksLayer[y][x] != null);
    }

    public boolean hasTankAt(TankModel tank, int x, int y) {
        return ((tanksLayer[y][x] != null) && (tank != tanksLayer[y][x].getModel()));
    }

    public boolean hasImpermeableTileAt(int x, int y) {
        if ((impermeableBlocksLayer[y][x] == 0)
                && (borderLayer[y][x] == 0)
                && (permeableBlocksLayer[y][x] == 0)) {
            return false;
        }
        return true;
    }

    public byte[][] getImpermeableBlocksLayer() {
        return impermeableBlocksLayer;
    }

    public TankController[][] getTanksLayer() {
        return tanksLayer;
    }

    public byte[][] getBorderLayer() {
        return borderLayer;
    }

    public void cler() {
        permeableBlocksLayer = new byte[height][width];
        impermeableBlocksLayer = new byte[height][width];
        borderLayer = new byte[height][width];
        tanksLayer = new TankController[height][width];
    }
}

