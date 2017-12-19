package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Model.BotTankModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.Utils.MathUtils;
import com.hvitalii.thetanksgame.View.BotTankView;

import java.util.Date;

public class BotTankController implements TankController {

    private GameController state;
    private BotTankModel model;
    private BotTankView view;
    private long lastShotTime;
    private float shift;

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType) {
        this(state, rectangle, atlas, botType, -1);
    }

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType, float direction) {
        this(state, rectangle, atlas, botType, -1);
        model.setDirection(direction);
    }

    public BotTankController(GameController state, Rectangle rectangle, TextureAtlas atlas, int botType, int bonusType) {
        this.state = state;
        model = new BotTankModel(rectangle, bonusType);
        model.setBotType(botType);
        view = new BotTankView(atlas, model);
        lastShotTime = 0;
        shift = 0;
    }

    @Override
    public void update(long frame) {
//        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//                move(Direction.UP);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//                move(Direction.DOWN);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//                move(Direction.LEFT);
//            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//                move(Direction.RIGHT);
//            }
//
//            if (Gdx.input.isKeyPressed(Input.Keys.X)){
//
//                long time = new Date().getTime();
//
//                if ((time - lastShotTime) > 100) {
//                    fire();
//                    lastShotTime = time;
//                }
//            }
//        }

        BattleFieldController battleField = state.getBattleField();
        float direction = model.getDirection();
        float random = direction;

        if (!model.isDirectionChanged()) {
            if ((int)(Math.random() * 64) % 64 == 0) {
                random = randomDirection();
            }
            if ((int)(Math.random() * 512) % 512 == 0) {
                System.out.println("direction changed");
                direction = randomDirection();
            }
        }

        if (direction == Direction.UP) {
            float newPosition = model.getY() + model.getSpeed();
            if (!battleField.isPositionFree(model, model.getX(), newPosition + Size.TANK)
                    || !battleField.isPositionFree(model, model.getX() + Size.TANK - 0.1f, newPosition + Size.TANK)
                    ) {
                random = randomDirection(random);
                move(random);
            } else {
                move(direction);
            }
        } else if (direction == Direction.DOWN) {
            float newPosition = model.getY() - model.getSpeed();
            if (!battleField.isPositionFree(model, model.getX(), newPosition)
                    || !battleField.isPositionFree(model, model.getX() + Size.TANK - 0.1f, newPosition)
                    ) {
                random = randomDirection(random);
                move(random);
            } else {
                move(direction);
            }
        } else if (direction == Direction.LEFT) {
            float newPosition = model.getX() - model.getSpeed();
            if (!battleField.isPositionFree(model, newPosition, model.getY())
                    || !battleField.isPositionFree(model, newPosition, model.getY() + Size.TANK - 0.1f)
                    ) {
                random = randomDirection(random);
                move(random);
            } else {
                move(direction);
            }
        } else if (direction == Direction.RIGHT) {
            float newPosition = model.getX() + model.getSpeed();
            if (!battleField.isPositionFree(model, newPosition  + Size.TANK, model.getY())
                    || !battleField.isPositionFree(model, newPosition + Size.TANK, model.getY() + Size.TANK - 0.1f)
                    ) {
                random = randomDirection(random);
                move(random);
            } else {
                move(direction);
            }
        }

        int fire = (int)(Math.random() * 64) % 64;
//        if (fire == 0) System.out.println("/////" + fire);
//        else System.out.println(fire);
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
        if ((time - lastShotTime) > 100) {
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
            if (model.getShieldEnergy() <= 0) {
                if (model.getArmourAmount() > 0) {
                    model.setArmourAmount(model.getArmourAmount() - 1);
                    view.armourAmountChanged();
                } else {
                    state.destructTank(this);
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

    private void move(float direction) {
        BattleFieldController battleField = state.getBattleField();
        model.move(battleField, direction);
    }

    private float randomDirection() {
        return (int)((Math.random() * 359) / 90) * 90;
    }

    private float randomDirection(float direction) {
        if ((model.getDirection() == direction) || (model.getDirection() == Math.abs(direction - 180))) {
            if ((int)(Math.random() * 64) % 32 == 0) {
                if ((Math.random() * 100) > 50) {
                    direction += 90;
                } else {
                    direction -= 90;
                }
            }
        }
        direction = Math.abs(direction);
        direction = (direction == 360) ? 0 : direction;
        return direction;
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
