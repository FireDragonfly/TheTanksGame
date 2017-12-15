package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

abstract class TankModel extends Moveable {

    private int bulletsMax;
    private int bulletsAmount;
    private int bulletsType;
    private int shieldEnergy;

    private int animationFrame;

    public TankModel(Rectangle rectangle, float direction, float speed) {
        this(rectangle, direction, speed, 1, BulletsTypes.NORMAL, 0);
    }

    public TankModel(Rectangle rectangle, float direction, float speed, int bulletsAmount, int bulletsType, int shieldEnergy) {
        super(rectangle, direction, speed);
        setBulletsAmount(bulletsAmount);
        setBulletsType(bulletsType);
        setShieldEnergy(shieldEnergy);
        bulletsMax = 1;
    }

    public int getBulletsAmount() {
        return bulletsAmount;
    }

    public void setBulletsAmount(int bulletsAmount) {
        if (bulletsAmount > 0) {
            this.bulletsAmount = bulletsAmount;
        } else {
            this.bulletsAmount = 1;
        }
    }

    public void addBullet() {
        if (bulletsAmount < bulletsMax) {
            bulletsAmount++;
        }
    }

    public void removeBullet() {
        if (bulletsAmount > 0) {
            bulletsAmount--;
        }
    }

    public int getBulletsType() {
        return bulletsType;
    }

    public void setBulletsType(int bulletsType) {
        if ((bulletsType >= BulletsTypes.NORMAL) && (bulletsType <= BulletsTypes.AP)) {
            this.bulletsType = bulletsType;
        } else {
            this.bulletsType = BulletsTypes.NORMAL;
        }

    }

    public void setBulletsMax(int bulletsMax) {
        this.bulletsMax = bulletsMax;
    }

    public int getBulletsMax() {
        return bulletsMax;
    }

    public int getShieldEnergy() {
        return shieldEnergy;
    }

    public void setShieldEnergy(int shieldEnergy) {
        if (shieldEnergy >= 0) {
            this.shieldEnergy = shieldEnergy;
        } else {
            this.shieldEnergy = 0;
        }
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void switchAnimationFrame() {
        animationFrame = (animationFrame == 0) ? 1 : 0 ;
    }
}
