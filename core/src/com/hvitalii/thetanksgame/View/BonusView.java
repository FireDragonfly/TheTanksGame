package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BonusModel;

public class BonusView {

    private Sprite[] sprites;

    public BonusView(TextureAtlas atlas) {
        sprites = new Sprite[7];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Sprite(atlas.findRegion("bonus", i), 0, 0, (int) Size.BLOCK, (int) Size.BLOCK);
            sprites[i].setOriginCenter();
        }
    }

    public void draw(SpriteBatch batch, BonusModel bonusModel) {
        sprites[bonusModel.getType()].setPosition(bonusModel.x, bonusModel.y);
        sprites[bonusModel.getType()].draw(batch);
    }
}
