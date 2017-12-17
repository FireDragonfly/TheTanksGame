package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Model.TankModel;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.Utils.MathUtils;
import com.hvitalii.thetanksgame.View.PlayerTankView;

import java.util.Date;


public class PlayerTankController implements TankController {

    private GameScreen screen;
    private PlayerTankModel model;
    private PlayerTankView view;
    private long lastShotTime;

    public PlayerTankController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int playerNumber) {
        this(screen, rectangle, atlas, playerNumber, Levels.FIRST);
    }

    public PlayerTankController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int playerNumber, int level) {
        this.screen = screen;
        model = new PlayerTankModel(rectangle, playerNumber, level);
        view = new PlayerTankView(atlas, model);
        lastShotTime = 0;
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (!model.isLocked())
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move(Direction.UP);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move(Direction.DOWN);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move(Direction.LEFT);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move(Direction.RIGHT);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.X)){

                long time = new Date().getTime();

                if ((time - lastShotTime) > 100) {
                    fire();
                    lastShotTime = time;
                }
            }
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
    public Rectangle getBounds() {
        return new Rectangle(model);
    }

    @Override
    public TankModel getModel() {
        return model;
    }

    @Override
    public boolean hitOn(BulletController bullet) {
        if (bullet.getOwnerType() != Types.USER){
            if (model.getShieldEnergy() <= 0) {
                screen.destructTank(this);
            }
        } else {
            model.lock();
        }
        return true;
    }

    private void fire() {
        long time = new Date().getTime();
        if ((time - lastShotTime) > 100) {
            if (model.getBulletsAmount() > 0) {
                model.removeBullet();
                BulletController bullet = new BulletController(
                        screen,
                        bulletStartPosition(),
                        view.getAtlas(),
                        model.getBulletsType(),
                        model.getDirection(),
                        this,
                        Types.USER
                );
                screen.spawnBullet(bullet);
                lastShotTime = time;
            }
        }
    }

    private void move(float direction) {
        BattleFieldController battleField = screen.getBattleField();
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
}
