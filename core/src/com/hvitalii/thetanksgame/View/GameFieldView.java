package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Model.GameFieldModel;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

import com.hvitalii.thetanksgame.Constants.GameConstants.*;

public class GameFieldView {

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private GameFieldModel model;

    public GameFieldView(TextureAtlas atlas, GameFieldModel model) {
        this.atlas = atlas;
        this.model = model;
        sprites = new Sprite[40];
        for (int i = 0; i < sprites.length; i++){
            sprites[i] = new Sprite(atlas.findRegion("block", i));
            sprites[i].setSize(Resolution.TILE_SIZE, Resolution.TILE_SIZE);
            sprites[i].setOriginCenter();
        }
    }

    public void drawBottomLayers(SpriteBatch batch) {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                switch (model.get(j, i)) {
                    case TilesTypes.BORDER:
                        updateAndDrawSprite(batch, TilesTypes.BORDER, j, i);
                        break;
                    case TilesTypes.BRICK:
                        updateAndDrawSprite(batch, TilesTypes.BRICK, j, i);
                        break;
                    case TilesTypes.DESTROYED_BRICK:
                        updateAndDrawSprite(batch, TilesTypes.DESTROYED_BRICK, j, i);
                        break;
                    case TilesTypes.CONCRETE:
                        updateAndDrawSprite(batch, TilesTypes.CONCRETE, j, i);
                        break;
                    case TilesTypes.WATER_1:
                        updateAndDrawSprite(batch, TilesTypes.WATER_1, j, i);
                        break;
                    case TilesTypes.WATER_2:
                        updateAndDrawSprite(batch, TilesTypes.WATER_2, j, i);
                        break;
                    case TilesTypes.ICE:
                        updateAndDrawSprite(batch, TilesTypes.ICE, j, i);
                        break;
                    case TilesTypes.EAGLE_0_0:
                        updateAndDrawSprite(batch, TilesTypes.EAGLE_0_0, j, i);
                        break;
                    case TilesTypes.EAGLE_1_0:
                        updateAndDrawSprite(batch, TilesTypes.EAGLE_1_0, j, i);
                        break;
                    case TilesTypes.EAGLE_0_1:
                        updateAndDrawSprite(batch, TilesTypes.EAGLE_0_1, j, i);
                        break;
                    case TilesTypes.EAGLE_1_1:
                        updateAndDrawSprite(batch, TilesTypes.EAGLE_1_1, j, i);
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_0:
                        updateAndDrawSprite(batch, TilesTypes.DESTROYED_EAGLE_0_0, j, i);
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_0:
                        updateAndDrawSprite(batch, TilesTypes.DESTROYED_EAGLE_1_0, j, i);
                        break;
                    case TilesTypes.DESTROYED_EAGLE_0_1:
                        updateAndDrawSprite(batch, TilesTypes.DESTROYED_EAGLE_0_1, j, i);
                        break;
                    case TilesTypes.DESTROYED_EAGLE_1_1:
                        updateAndDrawSprite(batch, TilesTypes.DESTROYED_EAGLE_1_1, j, i);
                        break;
                }
            }
        }
    }

    public void drawTankLayer(SpriteBatch batch) {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if (model.hasTankAt(j, i)) {
                    updateAndDrawSprite(batch, TilesTypes.PLAYER_IMG, j, i);
                }
            }
        }
    }

    public void drawTopLayers(SpriteBatch batch) {
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                switch (model.get(j, i)) {
                    case TilesTypes.GRASS:
                        updateAndDrawSprite(batch, TilesTypes.GRASS, j, i);
                        break;
                    case TilesTypes.BOT_IMG:
                        updateAndDrawSprite(batch, TilesTypes.BOT_IMG, j, i);
                        break;
                    case TilesTypes.PLAYER_IMG:
                        updateAndDrawSprite(batch, TilesTypes.PLAYER_IMG, j, i);
                        break;
                }
            }
        }
    }

    public void drawUi(SpriteBatch batch) {
        byte[][] layer = model.getUiLayer();
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                updateAndDrawSprite(batch, layer[i][j], j, i);
            }
        }
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setModel(GameFieldModel model) {
        this.model = model;
    }

    private void updateAndDrawSprite(SpriteBatch batch, int spriteIndex, int x, int y) {
        if (spriteIndex > 0) {
            sprites[spriteIndex].setPosition(x * Resolution.TILE_SIZE, y * Resolution.TILE_SIZE);
            sprites[spriteIndex].draw(batch);
        }
    }

}
