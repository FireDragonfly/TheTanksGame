package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Controller {
    void update(long frame);
    void draw(SpriteBatch batch);
}