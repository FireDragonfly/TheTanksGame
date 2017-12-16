package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BotTankModel;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.View.BotTankView;

import java.util.Date;

public class BotTankController implements TankController {

    private GameScreen screen;
    private BotTankModel model;
    private BotTankView view;
    private long lastShotTime;

    public BotTankController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int botType) {
        this(screen, rectangle, atlas, botType, -1);
    }

    public BotTankController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int botType, int bonusType) {
        this.screen = screen;
        model = new BotTankModel(rectangle, bonusType);
        model.setBotType(botType);
        view = new BotTankView(atlas, model);
        lastShotTime = 0;
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
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

    private void fire() {
        if (model.getBulletsAmount() > 0) {
            model.removeBullet();
            BulletController bullet = new BulletController(
                    screen,
                    bulletStartPosition(),
                    view.getAtlas(),
                    model.getBulletsType(),
                    model.getDirection(),
                    this
            );
            screen.spawnBullet(bullet);
        }
    }

    private void move(float direction) {
        model.switchAnimationFrame();
        model.setDirection(direction);
        if (direction == Direction.UP) {
            float newPosition = model.getY() + model.getSpeed();
            model.setY(newPosition);
        } else if (direction == Direction.DOWN) {
            float newPosition = model.getY() - model.getSpeed();
            model.setY(newPosition);
        } else if (direction == Direction.LEFT) {
            float newPosition = model.getX() - model.getSpeed();
            model.setX(newPosition);
        } else if (direction == Direction.RIGHT) {
            float newPosition = model.getX() + model.getSpeed();
            model.setX(newPosition);
        }
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
