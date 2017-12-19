package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Controller {
    public void update(long frame);
    public void draw(SpriteBatch batch);
}