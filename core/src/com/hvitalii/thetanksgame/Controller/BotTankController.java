package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Model.BotTankModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.View.BotTankView;

import java.util.Date;

public class BotTankController implements TankController {

    private GameController state;
    private BotTankModel model;
    private BotTankView view;
    private long lastShotTime;
    private float shift;

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType) {
        this(state, rectangle, atlas, botType, false);
    }

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType, float direction) {
        this(state, rectangle, atlas, botType, false);
        model.setDirection(direction);
    }

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType, boolean isBonusCarrier) {
        this.state = state;
        model = new BotTankModel(rectangle, isBonusCarrier);
        model.setBotType(botType);
        view = new BotTankView(atlas, model);
        lastShotTime = 0;
        shift = 0;
    }

    @Override
    public void update(long frame) {
        float direction = model.getDirection();
        float random = direction;

        if (isThePathFree(direction)) {
            if ((int)(Math.random() * 200) % 200 == 0) {
                direction = randomDirection();
            }
        } else if (!model.isDirectionChanged()){
            direction = randomDirection();
        }

        move(direction);

        int fire = (int)(Math.random() * 32) % 32;
        if (fire == 0) {
            fire();
        }

    }

    @Override
    public void draw(SpriteBatch batch) {
        view.draw(batch);
    }

    @Override
    public void bulletDestroyed() {
        model.addBullet();
    }

    private void fire() {
        long time = new Date().getTime();
        if ((time - lastShotTime) > GameConstants.FIRE_RATE) {
            if (model.getBulletsAmount() > 0) {
                model.removeBullet();
                BulletController bullet = new BulletController(
                        state,
                        bulletStartPosition(),
                        view.getAtlas(),
                        model.getBulletsType(),
                        model.getDirection(),
                        this,
                        Types.BOT
                );
                state.spawnBullet(bullet);
                lastShotTime = time;
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(model);
    }

    @Override
    public TankModel getModel() {
        return model;
    }

    @Override
    public boolean hitOn(BulletController bullet) {
        if (bullet.getOwnerType() != Types.BOT){
            if (model.getShieldActiveTime() < new Date().getTime()) {
                if (model.getArmourAmount() > 0) {
                    model.setArmourAmount(model.getArmourAmount() - 1);
                    view.armourAmountChanged();
                } else {
                    state.destructTank(this, bullet);
                }
                if (model.isBonusCarrier()) {
                    state.spawnRandomBonus();
                    model.setBonusCarrier(false);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int getType() {
        return Types.BOT;
    }
    public int getBotType() {
        return model.getBotType();
    }

    private void move(float direction) {
        GameFieldController battleField = state.getBattleField();
        model.move(battleField, direction);
    }

    /**
     * generate random direction
     */
    private float randomDirection() {
        float direction = model.getDirection();

        if ((int)(Math.random() * 32) % 32 == 0) {
            direction = (int) ((Math.random() * 359) / 90) * 90;
        }

        if (isThePathFree(direction)) {
            return direction;
        } else  {
            if ((int)(Math.random() * 32) % 32 == 0) {
                if ((Math.random() * 100) > 50) {
                    direction += 90;
                } else {
                    direction -= 90;
                }
            }
            if (direction == Direction.UP) {
                if ((int)(Math.random() * 128) % 128 == 0) {
                    direction -= 180;
                }
            }
        }

        direction = Math.abs(direction);
        direction = (direction == 360) ? 0 : direction;
        return direction;
    }

    private boolean isThePathFree(float direction) {
        GameFieldController battleField = state.getBattleField();
        if (direction == Direction.UP) {
            float newPosition = model.getY() + model.getSpeed();
            if (battleField.isPositionFree(model, model.getX(), newPosition + Size.TANK)
                    && battleField.isPositionFree(model, model.getX() + Size.TANK - 0.1f, newPosition + Size.TANK)
                    ) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.DOWN) {
            float newPosition = model.getY() - model.getSpeed();
            if (battleField.isPositionFree(model, model.getX(), newPosition)
                    && battleField.isPositionFree(model, model.getX() + Size.TANK - 0.1f, newPosition)
                    ) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.LEFT) {
            float newPosition = model.getX() - model.getSpeed();
            if (battleField.isPositionFree(model, newPosition, model.getY())
                    && battleField.isPositionFree(model, newPosition, model.getY() + Size.TANK - 0.1f)
                    ) {
                return true;
            } else {
                return false;
            }
        } else if (direction == Direction.RIGHT) {
            float newPosition = model.getX() + model.getSpeed();
            if (battleField.isPositionFree(model, newPosition  + Size.TANK, model.getY())
                    && battleField.isPositionFree(model, newPosition + Size.TANK, model.getY() + Size.TANK - 0.1f)
                    ) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private Rectangle bulletStartPosition() {
        float positionX;
        float positionY;

        if (model.getDirection() == Direction.UP) {
            positionX = model.getX() + ((model.getWidth() - Size.BULLET) / 2);
            positionY = model.getY() + model.getHeight();
        } else if (model.getDirection() == Direction.DOWN) {
            positionX = model.getX() + ((model.getWidth() - Size.BULLET) / 2);
            positionY = model.getY() - Size.BULLET;
        } else if (model.getDirection() == Direction.LEFT) {
            positionX = model.getX() - Size.BULLET;
            positionY = model.getY() + ((model.getHeight() - Size.BULLET) / 2);
        } else {
            positionX = model.getX() + model.getWidth();
            positionY = model.getY() + ((model.getHeight() - Size.BULLET) / 2);
        }
        return new Rectangle(positionX, positionY, Size.BULLET, Size.BULLET);
    }
}
