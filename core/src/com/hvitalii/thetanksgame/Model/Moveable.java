package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;

public abstract class Moveable extends Rectangle {

    private float speed;
    private float direction;
    private float previousDirection;


    Moveable(Rectangle rectangle, float direction, float speed) {
        super(rectangle);
        this.speed = speed;
        this.direction = direction;
        previousDirection = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDirection(float direction) {
        previousDirection = this.direction;
        this.direction = direction;
    }

    public boolean isDirectionChanged() {
        return previousDirection != direction;
    }
}
