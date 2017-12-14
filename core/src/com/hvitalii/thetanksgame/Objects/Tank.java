package com.hvitalii.thetanksgame.Objects;

abstract class Tank extends MoveableObject{

    private int bulletsAmount;
    private int bulletsType;
    private int shieldEnergy;

    private int animationFrame;

    public Tank(int type, int direction, float speed) {
        this(type, direction, speed, 1, BulletsTypes.NORMAL, 0);
    }

    public Tank(int type, int direction, float speed, int bulletsAmount, int bulletsType, int shieldEnergy) {
        super(type, direction, speed);
        setBulletsAmount(bulletsAmount);
        setBulletsType(bulletsType);
        setShieldEnergy(shieldEnergy);
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
