package com.hvitalii.thetanksgame.MyOwn;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MOImgRadioButton extends MOImgButton {

    private boolean isActive;
    private MORadioButtonGroup group;
    private Color activeColor;
    private int id;

    public MOImgRadioButton(Texture texture, MORadioButtonGroup group) {
        super(texture);
        init(group);
    }

    public MOImgRadioButton(Texture texture, MORadioButtonGroup group, int id) {
        super(texture);
        init(group);
        this.id = id;
    }

    public MOImgRadioButton(TextureRegion region, MORadioButtonGroup group) {
        super(region);
        init(group);
    }

    public MOImgRadioButton(TextureRegion region, MORadioButtonGroup group, int id) {
        super(region);
        init(group);
        this.id = id;
    }

    public MOImgRadioButton(Sprite sprite, MORadioButtonGroup group) {
        super(sprite);
        init(group);
    }

    public MOImgRadioButton(Sprite sprite, MORadioButtonGroup group, int id) {
        super(sprite);
        init(group);
        this.id = id;
    }

    public void activate() {
        isActive = true;
        group.activate(this);
    }

    public void deactivate() {
        isActive = false;
        group.deactivate(this);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveColor(Color color) {
        activeColor = color;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (activeColor != null && isActive) {
            super.draw(batch, activeColor);
        } else {
            super.draw(batch);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update() {
        if (justTouched()) {

            activate();
        }
    }

    private void init(MORadioButtonGroup group) {
        isActive = false;
        this.group = group;
        id = -1;
        group.add(this);
    }
}
