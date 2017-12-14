package com.hvitalii.thetanksgame.Objects;


import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.*;

abstract class MoveableObject extends GameObject{

    private float speed;
    private int direction;

    MoveableObject(int type, int direction, float speed) {
        super(type);
        setSpeed(speed);
        setDirection(direction);
    }

    public float getSpeed() {
        return speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setSpeed(float speed) {
        if (speed > 0) {
            this.speed = speed;
        } else {
            this.speed = Speed.NORMAL;
        }
    }

    public void setDirection(int direction) {
        if((direction < Direction.LEFT + 1)&&(direction > Direction.UP - 1)) {
            this.direction = direction;
        } else {
            this.direction = Direction.UP;
        }
    }

    public abstract void move(int direction);
}
