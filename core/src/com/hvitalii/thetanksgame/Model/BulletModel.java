package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.BulletAttributes;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.TankController;

public class BulletModel extends Moveable{

    private int bulletType;
    private TankController owner;

    public BulletModel(Rectangle rectangle, int bulletType, float direction, TankController owner) {
        super(rectangle, direction, Speed.NORMAL_BULLET_SPEED);
        setBulletType(bulletType);
        this.owner = owner;
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
        if ((bulletType >= BulletsTypes.NORMAL) && (bulletType <= BulletsTypes.AP)) {
            this.bulletType = bulletType;
        } else {
            this.bulletType = BulletsTypes.NORMAL;
        }
        BulletAttributes.bulletTypes[bulletType].loadAttributes(this);
    }

    public TankController getOwner() {
        return owner;
    }
}
