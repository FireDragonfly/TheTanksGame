package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.graphics.Texture;

public class Tank extends GameObject {



    public Tank(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }

    public void setX(float x) {
        bounds.setX(x);
    }

    public void setY(float y) {
        bounds.setY(y);
    }
}
