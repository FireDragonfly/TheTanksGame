package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.Player;
import com.hvitalii.thetanksgame.View.PlayerTankView;

import java.util.Date;


public class PlayerTankController implements TankController {

    private Player player;
    private GameController state;
    private PlayerTankModel model;
    private PlayerTankView view;
    private int[] controlKeys;
    private long lastShotTime;
    private boolean isFired;

    public PlayerTankController(Player player, GameController state, Rectangle rectangle, TextureAtlas atlas, int playerNumber) {
        this(player, state, rectangle, atlas, playerNumber, Levels.FIRST);
    }

    public PlayerTankController(Player player, GameController state, Rectangle rectangle, TextureAtlas atlas, int playerNumber, int level) {
        this.player = player;
        this.state = state;
        model = new PlayerTankModel(rectangle, playerNumber, level);
        view = new PlayerTankView(atlas, model);
        controlKeys = ControlKeys.DEFAULT_PLAYER_KEYS[playerNumber];
        lastShotTime = 0;
        isFired = false;
    }

    @Override
    public void update(long frame) {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (!model.isLocked()) {
                if (Gdx.input.isKeyPressed(controlKeys[ControlKeys.KEY_UP])) {
                    move(Direction.UP);
                } else if (Gdx.input.isKeyPressed(controlKeys[ControlKeys.KEY_DOWN])) {
                    move(Direction.DOWN);
                } else if (Gdx.input.isKeyPressed(controlKeys[ControlKeys.KEY_LEFT])) {
                    move(Direction.LEFT);
                } else if (Gdx.input.isKeyPressed(controlKeys[ControlKeys.KEY_RIGH])) {
                    move(Direction.RIGHT);
                }
            }

            if (Gdx.input.isKeyPressed(controlKeys[ControlKeys.KEY_FIRE])){
                long time = new Date().getTime();

                if ((time - lastShotTime) > 100 && !isFired) {
                    isFired = true;
                    fire();
                    lastShotTime = time;
                }
            } else {
                isFired = false;
            }
        } else {
            isFired = false;
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

    @Override
    public boolean hitOn(BulletController bullet) {
        if (bullet.getOwnerType() != Types.USER){
            if (model.getShieldEnergy() <= 0) {
                state.destructTank(this, bullet);
            }
        } else {
            if (bullet.getOwner() != this) {
                model.lock();
            }
        }
        return true;
    }

    public void reset(Rectangle rectangle) {
        model.setDirection(Direction.UP);
        model.set(rectangle);
        model.reset();
        view.levelChanged();
        model.setDirection(Direction.UP);//reset previous direction
    }

    public void lock() {
        model.lock();
    }

    public void setControlKeys(int[] controlKeys) {
        this.controlKeys = controlKeys;
    }

    @Override
    public int getType() {
        return Types.USER;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(model);
    }

    @Override
    public TankModel getModel() {
        return model;
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
                        Types.USER
                );
                state.spawnBullet(bullet);
                lastShotTime = time;
            }
        }
    }

    private void move(float direction) {
        GameFieldController battleField = state.getBattleField();
        model.move(battleField, direction);
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

    public Player getPlayer() {
        return player;
    }
}
