package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Model.GameFieldModel;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.Player;
import com.hvitalii.thetanksgame.StageState;
import com.hvitalii.thetanksgame.View.GameFieldView;

import java.awt.*;

import static com.hvitalii.thetanksgame.Utils.MathUtils.*;


public class GameFieldController {

    private StageState state;
    private GameController game;
    private GameFieldModel model;
    private GameFieldView view;

    public GameFieldController(GameController game, StageState state, TextureAtlas atlas) {
        this.state = state;
        this.game = game;
        model = new GameFieldModel();
        view = new GameFieldView(atlas, model);
    }

    public void setMap(FileHandle fileHandle) {
        model.loadMap(fileHandle);
    }

    public void update(long frame) {
        tankPositionUpdate();
        uiUpdate();
//        bulletPositionUpdate();
        if (frame % 32 == 0) {
            waterAnimationUpdate();
        }
    }

    public boolean hitTank(BulletController bullet, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;
        TankController tank = model.getTank(trimmedX, trimmedY);
        if (tank != null) {
            return tank.hitOn(bullet);
        }
        return false;
    }

    public boolean hitBlock(BulletController bullet, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;


        byte type = model.getImpermeableForBulletTileAt(trimmedX, trimmedY);

        switch (type) {
            case TilesTypes.NULL:
            case TilesTypes.WATER_1:
            case TilesTypes.WATER_2:
            case TilesTypes.ICE:
            case TilesTypes.GRASS:
            case TilesTypes.BOT_IMG:
            case TilesTypes.PLAYER_IMG:
                return false;
        }

        switch (type) {
            case TilesTypes.BORDER:
                break;
            case TilesTypes.BRICK:
                model.set(TilesTypes.DESTROYED_BRICK, trimmedX, trimmedY);
                break;
            case TilesTypes.DESTROYED_BRICK:
                model.set(TilesTypes.NULL, trimmedX, trimmedY);
                break;
            case TilesTypes.CONCRETE:
                if (bullet.getType() == BulletsTypes.AP){
                    model.set(TilesTypes.NULL, trimmedX, trimmedY);
                }
                break;
            case TilesTypes.EAGLE_0_0:
                destructEagle(trimmedX, trimmedY);
                break;
            case TilesTypes.EAGLE_1_0:
                destructEagle(trimmedX - 1, trimmedY);
                break;
            case TilesTypes.EAGLE_0_1:
                destructEagle(trimmedX, trimmedY - 1);
                break;
            case TilesTypes.EAGLE_1_1:
                destructEagle(trimmedX - 1, trimmedY - 1);
                break;
//            case TilesTypes.DESTROYED_EAGLE_0_0:
//                break;
//            case TilesTypes.DESTROYED_EAGLE_1_0:
//                break;
//            case TilesTypes.DESTROYED_EAGLE_0_1:
//                break;
//            case TilesTypes.DESTROYED_EAGLE_1_1:
//                break;
        }
        return true;
    }

    public boolean isPositionFree(TankModel tank, float x, float y) {
        int trimmedX = (int) trimToGrid(x) / 8;
        int trimmedY = (int) trimToGrid(y) / 8;
        return !model.hasImpermeableTileAt(trimmedX, trimmedY) && !model.hasTankAt(tank, trimmedX, trimmedY);
    }

    public Point getRandomFreePosition() {
        int random = (int) (Math.random() * ((model.getWidth() - 7) * (model.getHeight() - 6)));
        while (random > 0) {

            for (int i = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x;
                 i <= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.y; i++) {
                for (int j = Resolution.BATTLE_FIELD_LEFT_BOTTOM_POINT.x + 1;
                     j <= Resolution.BATTLE_FIELD_RIGHT_TOP_POINT.x - 1; j++) {
                    if (!model.hasIndestructibleTileAt(j, i)) {
                        random--;
                        if (random <= 0) {
                            return new Point(j, i);
                        }
                    }
                }
            }
        }
        return null;
    }

    public TankController getTankAt(int x, int y) {
        return model.getTank(x, y);
    }

    public void drawBottomLayers(SpriteBatch batch) {
        view.drawBottomLayers(batch);
    }

    public void drawTopLayers(SpriteBatch batch) {
        view.drawTopLayers(batch);
    }

    public void drawUi(SpriteBatch batch) {
        view.drawUi(batch);
    }

    public void surroundBases(byte blockType) {
        Array<Point> bases = model.getBases();
        for (int i = 0; i < bases.size; i++) {
            model.surroundBase(blockType, bases.get(i));
        }
    }

    private void tankPositionUpdate() {
        model.clearTankLayer();

        Array<TankController> botsTanks = state.getBots();
        Array<TankController> playersTanks = state.getPlayers();
        TankController[][] tanksLayer = model.getTanksLayer();

        Rectangle bounds;
        int x;
        int y;

        for (int i = 0; i < playersTanks.size; i++) {
            bounds = playersTanks.get(i).getBounds();
            x = (int) clingToGrid(bounds.x) / 8;
            y = (int) clingToGrid(bounds.y) / 8;
//            if ((x > width) || (x < 0) || (y > height) || (y < 0)) {
//                return;
//            }
            for (int r = 0; r < 2; r++){
                for (int c = 0; c < 2; c++) {
                    tanksLayer[y + r][x + c] = playersTanks.get(i);
                }
            }
        }for (int i = 0; i < botsTanks.size; i++) {
            bounds = botsTanks.get(i).getBounds();
            x = (int) clingToGrid(bounds.x) / 8;
            y = (int) clingToGrid(bounds.y) / 8;
            for (int r = 0; r < 2; r++){
                for (int c = 0; c < 2; c++) {
                    tanksLayer[y + r][x + c] = botsTanks.get(i);
                }
            }
        }
    }

    private void waterAnimationUpdate() {
        byte[][] impermeableBlocksLayer = model.getBottomBlocksLayer();
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

    private void uiUpdate() {
        model.clearUiLayer();

        byte[][] layer = model.getUiLayer();
        int yStartPosition = model.getHeight() - 3;
        int xStartPosition = model.getWidth() - 3;

        int botCount = state.getBots().size;
        layer[yStartPosition][xStartPosition] = TilesTypes.BOT_IMG;
        int[] digits = ObjectsConstants.intToTiles(state.getBotsRemaining());
        for (int i = 0; i < digits.length; i++) {
            layer[yStartPosition - 1][xStartPosition + i] = (byte) digits[i];
        }

        Array<Player> players = game.getPlayers();
        for (int i = 0; i < players.size; i++) {
            layer[yStartPosition - (4 + i * 4)][xStartPosition] =
                    (byte) (players.get(i).getNumber() + 1 + TilesTypes.DIGIT_0);
            layer[yStartPosition - (4 + i * 4)][xStartPosition + 1] = TilesTypes.LETTER_P;
            layer[yStartPosition - (5 + i * 4)][xStartPosition] = TilesTypes.PLAYER_IMG;
            layer[yStartPosition - (5 + i * 4)][xStartPosition + 1] =
                    (byte) (((PlayerTankModel)players.get(i).getController().getModel()).getLivesAmount() + TilesTypes.DIGIT_0);
        }
    }

    private void destructEagle(int x, int y) {
        model.set(TilesTypes.DESTROYED_EAGLE_0_0, x, y);
        model.set(TilesTypes.DESTROYED_EAGLE_1_0, x + 1, y);
        model.set(TilesTypes.DESTROYED_EAGLE_0_1, x, y + 1);
        model.set(TilesTypes.DESTROYED_EAGLE_1_1, x + 1, y + 1);
        if (!model.hasAliveBase()) {
            state.eagleKilled();
        }
    }
}
