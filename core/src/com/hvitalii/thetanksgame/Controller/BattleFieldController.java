package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BattleFieldModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.View.BattleFieldView;

import static com.hvitalii.thetanksgame.Utils.MathUtils.*;


public class BattleFieldController{

    private GameScreen screen;
    private BattleFieldModel model;
    private BattleFieldView view;

    public BattleFieldController(GameScreen screen, TextureAtlas atlas) {
        this.screen = screen;
        model = new BattleFieldModel();
        view = new BattleFieldView(atlas, model);
    }

    public void setMap(FileHandle fileHandle) {
        model.loadMap(fileHandle);
    }

    public void update() {
        tankPositionUpdate();
        waterAnimationUpdate();
    }

    public void drawBottomLayers(SpriteBatch batch) {
        view.drawBottomLayers(batch);
    }

    public void drawTopLayers(SpriteBatch batch) {
        view.drawTopLayers(batch);
    }

    public boolean hitTank(BulletController bullet, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;
        TankController tank = model.getTank(trimmedX, trimmedY);
        if (tank != null) {
            tank.hitOn(bullet);
            return true;
        }
        return false;
    }

    public boolean hitBlock(BulletController bullet, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;
        return model.hasImpermeableTileAt(trimmedX, trimmedY);
    }

    public boolean isPositionFree(TankModel tank, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;
        return model.hasImpermeableTileAt(trimmedX, trimmedY) && !model.hasTankAt(tank, trimmedX, trimmedY);
    }

    private void tankPositionUpdate() {
        clearTankLayer();

        Array<TankController> tanks = screen.getTanks();
        TankController[][] tanksLayer = model.getTanksLayer();

        Rectangle bounds;
        int x;
        int y;

        for (int i = 0; i < tanks.size; i++) {
            bounds = tanks.get(i).getBounds();
            x = (int) clingToGrid(bounds.x) / 8;
            y = (int) clingToGrid(bounds.y) / 8;
            for (int r = 0; r < 2; r++){
                for (int c = 0; c < 2; c++) {
                    tanksLayer[y + r][x + c] = tanks.get(i);
                }
            }
        }
    }

    private void waterAnimationUpdate() {
        byte[][] impermeableBlocksLayer = model.getImpermeableBlocksLayer();
        for (int i = 0; i < impermeableBlocksLayer.length; i++) {
            for (int j = 0; j < impermeableBlocksLayer.length; j++) {
                switch (impermeableBlocksLayer[i][j]) {
                    case TilesTypes.WATER_1:
                        impermeableBlocksLayer[i][j] = TilesTypes.WATER_2;
                        break;
                    case TilesTypes.WATER_2:
                        impermeableBlocksLayer[i][j] = TilesTypes.WATER_1;
                        break;
                }
            }
        }
    }

    private void clearTankLayer() {
        TankController[][] tanksLayer = model.getTanksLayer();
        for (int i = 0; i < tanksLayer.length; i++) {
            for (int j = 0; j < tanksLayer[i].length; j++) {
                tanksLayer[i][j] = null;
            }
        }
    }
}
