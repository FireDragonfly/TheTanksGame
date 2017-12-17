package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Model.BattleFieldModel;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

import com.hvitalii.thetanksgame.Constants.GameConstants.*;

public class BattleFieldView {

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private BattleFieldModel model;

    public BattleFieldView(TextureAtlas atlas, BattleFieldModel model) {
        this.atlas = atlas;
        this.model = model;
        sprites = new Sprite[19];
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
                    case TilesTypes.GRAY:
                        updateAndDrawSprite(batch, TilesTypes.GRAY, j, i);
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

    private void updateAndDrawSprite(SpriteBatch batch, int spriteIndex, int x, int y) {
        sprites[spriteIndex].setPosition(x * Resolution.TILE_SIZE, y * Resolution.TILE_SIZE);
        sprites[spriteIndex].draw(batch);
    }

}
