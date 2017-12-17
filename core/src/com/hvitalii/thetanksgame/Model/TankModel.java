package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.BattleFieldController;
import com.hvitalii.thetanksgame.Utils.MathUtils;

public abstract class TankModel extends Moveable {

    private int bulletsMax;
    private int bulletsAmount;
    private int bulletsType;
    private int shieldEnergy;

    private int animationFrame;
    private float shift;

    public TankModel(Rectangle rectangle, float direction, float speed) {
        this(rectangle, direction, speed, 1, BulletsTypes.NORMAL, 0);
    }

    public TankModel(Rectangle rectangle, float direction, float speed, int bulletsAmount, int bulletsType, int shieldEnergy) {
        super(rectangle, direction, speed);
        setBulletsAmount(bulletsAmount);
        setBulletsType(bulletsType);
        setShieldEnergy(shieldEnergy);
        bulletsMax = 1;
        animationFrame = 0;
        shift = 0;
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

    public void move(BattleFieldController battleField, float direction) {

        shift += getSpeed();
        if (shift >= 1) {
            switchAnimationFrame();
            shift = 0;
        }

        setDirection(direction);

        if (direction == Direction.UP) {
            float newPosition = getY() + getSpeed();

            if (battleField.isPositionFree(this, getX(), newPosition + Size.TANK)
                    && battleField.isPositionFree(this, getX() + Size.TANK - 0.1f, newPosition + Size.TANK)
                    ) {
                setY(newPosition);
            } else {
                setY(MathUtils.clingToGrid(getY()));
            }
            if (isDirectionChanged()) {
                setX(MathUtils.clingToGrid(getX()));
            }

        } else if (direction == Direction.DOWN) {
            float newPosition = getY() - getSpeed();

            if (battleField.isPositionFree(this, getX(), newPosition)
                    && battleField.isPositionFree(this, getX() + Size.TANK - 0.1f, newPosition)
                    ) {
                setY(newPosition);
            } else {
                setY(MathUtils.clingToGrid(getY()));
            }
            if (isDirectionChanged()) {
                setX(MathUtils.clingToGrid(getX()));
            }

        } else if (direction == Direction.LEFT) {
            float newPosition = getX() - getSpeed();

            if (battleField.isPositionFree(this, newPosition, getY())
                    && battleField.isPositionFree(this, newPosition, getY() + Size.TANK - 0.1f)
                    ) {
                setX(newPosition);
            } else {
                setX(MathUtils.clingToGrid(getX()));
            }

            if (isDirectionChanged()) {
                setY(MathUtils.clingToGrid(getY()));
            }

        } else if (direction == Direction.RIGHT) {
            float newPosition = getX() + getSpeed();

            if (battleField.isPositionFree(this, newPosition  + Size.TANK, getY())
                    && battleField.isPositionFree(this, newPosition + Size.TANK, getY() + Size.TANK - 0.1f)
                    ) {
                setX(newPosition);
            } else {
                setX(MathUtils.clingToGrid(getX()));
            }

            if (isDirectionChanged()) {
                setY(MathUtils.clingToGrid(getY()));
            }

        }
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void switchAnimationFrame() {
        animationFrame = (animationFrame == 0) ? 1 : 0 ;
    }
}
