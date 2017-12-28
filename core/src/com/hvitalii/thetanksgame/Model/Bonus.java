package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class Bonus extends Rectangle{

    private int type;

    public Bonus(float x, float y, int type) {
        super(x, y, Size.BLOCK, Size.BLOCK);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
