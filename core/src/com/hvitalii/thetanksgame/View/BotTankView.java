package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Model.BotTankModel;

import java.util.Date;

public class BotTankView {

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private Sprite[] shieldSprites;
    private BotTankModel model;
    private int frame;

    public BotTankView(TextureAtlas atlas, BotTankModel model) {
        sprites = new Sprite[2];
        shieldSprites = new Sprite[2];
        frame = 0;
        this.atlas = atlas;
        this.model = model;
        for (int i = 0; i < 2; i++) {
            sprites[i] = new Sprite(atlas.findRegion(GameConstants.BOT_TYPES_NAMES[model.getBotType()], i));
            sprites[i].setBounds(model.x, model.y, model.width, model.height);
            if (model.isBonusCarrier()) {
                sprites[i].setColor(Colors.BONUSED_TANK);
            } else {
                sprites[i].setColor(Colors.ARMOUR_COLORS[model.getArmourAmount()]);
            }
            sprites[i].setOriginCenter();
            sprites[i].setRotation(model.getDirection());

            shieldSprites[i] = new Sprite(atlas.findRegion("shield", i));
            shieldSprites[i].setBounds(model.x, model.y, model.width, model.height);
            shieldSprites[i].setOriginCenter();
        }
    }

    public void draw(SpriteBatch batch) {
        updateFrame();
        updateSprites();
        sprites[model.getAnimationFrame()].draw(batch);
        if (model.getShieldActiveTime() >= new Date().getTime()) {
            shieldSprites[frame % 2].draw(batch);
        }
    }

    public void armourAmountChanged() {
        for (int i = 0; i < 2; i++) {
            sprites[i].setColor(Colors.ARMOUR_COLORS[model.getArmourAmount()]);
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
            shieldSprites[i].setX(model.getX());
            shieldSprites[i].setY(model.getY());
        }
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    private void updateFrame() {
        frame++;
        if (frame > 4) {
            frame = 0;
        }
    }
}
