package com.hvitalii.thetanksgame.Objects;

public class Tank extends GameObject {



    public Tank(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public void setX(float x) {
        bounds.setX(x);
    }

    public void setY(float y) {
        bounds.setY(y);
    }
}
