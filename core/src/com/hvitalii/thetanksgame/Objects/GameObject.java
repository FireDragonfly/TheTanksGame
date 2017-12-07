package com.hvitalii.thetanksgame.Objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

abstract class GameObject {

    protected Rectangle bounds;

    GameObject(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    GameObject(Rectangle rectangle) {
        bounds = new Rectangle(rectangle);
    }

    public void setPosition(float x, float y) {
        bounds.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {

    }
}
