package com.hvitalii.thetanksgame.Objects;


import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.*;

abstract class MoveableObject extends GameObject{




    protected float speed;
    protected int direction;

    MoveableObject(float x, float y, float width, float height, float speed, int direction) {
        super(x, y, width, height);
        setSpeed(speed);
        setDirection(direction);
    }

    MoveableObject(Rectangle rectangle) {
        super(rectangle);
        setSpeed(speed);
        setDirection(direction);
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
}
