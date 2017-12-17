package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants;
import com.hvitalii.thetanksgame.Model.BotTankModel;

public class BotTankView {

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private BotTankModel model;

    public BotTankView(TextureAtlas atlas, BotTankModel model) {
        sprites = new Sprite[2];
        this.atlas = atlas;
        this.model = model;
        for (int i = 0; i < 2; i++) {
            sprites[i] = new Sprite(atlas.findRegion(ObjectsConstants.BOT_TYPES_NAMES[model.getBotType()], i));
            sprites[i].setBounds(model.x, model.y, model.width, model.height);
            if (model.isBonusCarrier()) {
                sprites[i].setColor(ObjectsConstants.Colors.BONUSED_TANK);
            } else {
                sprites[i].setColor(ObjectsConstants.Colors.ARMOUR_COLORS[model.getArmourAmount()]);
            }
            sprites[i].setOriginCenter();
            sprites[i].setRotation(model.getDirection());
        }
    }

    public void draw(SpriteBatch batch) {
        updateSprites();
        sprites[model.getAnimationFrame()].draw(batch);

    }

    public void armourAmountChanged() {
        for (int i = 0; i < 2; i++) {
            sprites[i].setColor(ObjectsConstants.Colors.ARMOUR_COLORS[model.getArmourAmount()]);
        }
    }

    private void updateSprites() {
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
