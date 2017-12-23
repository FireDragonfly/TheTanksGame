package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;

public class PlayerTankView{

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private PlayerTankModel model;

    public PlayerTankView(TextureAtlas atlas, PlayerTankModel model) {
        sprites = new Sprite[2];
        this.atlas = atlas;
        this.model = model;
        for (int i = 0; i < 2; i++) {
            sprites[i] = new Sprite(atlas.findRegion(GameConstants.PLAYER_TANKS_NAMES[model.getLevel()], i));
            sprites[i].setBounds(model.x, model.y, model.width, model.height);
            sprites[i].setColor(Colors.PLAYERS_COLORS[model.getPlayerNumber()]);
            sprites[i].setOriginCenter();
        }
    }

    public void draw(SpriteBatch batch) {
        updateSprites();
        sprites[model.getAnimationFrame()].draw(batch);
    }

    public void levelChanged() {
        for (int i = 0; i < 2; i++) {
            sprites[i].setRegion(atlas.findRegion(GameConstants.PLAYER_TANKS_NAMES[model.getLevel()], i));
        }
    }

    public void updateSprites() {
        if (model.isDirectionChanged()) {
            for (int i = 0; i < 2; i++) {
                sprites[i].setRotation(model.getDirection());
            }
        }
        for (int i = 0; i < 2; i++) {
            sprites[i].setX(model.getX());
            sprites[i].setY(model.getY());
        }
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
