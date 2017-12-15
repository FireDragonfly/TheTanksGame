package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BulletModel;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.View.BulletView;

public class BulletController implements Controller{

    private GameScreen screen;
    private BulletModel model;
    private BulletView view;

    public BulletController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int bulletType, float direction, TankController owner) {
        this.screen = screen;
        model = new BulletModel(rectangle, bulletType, direction, owner);
        view = new BulletView(atlas, model);
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void draw(SpriteBatch batch) {
        view.draw(batch);
    }

    private void move() {
        if ((model.getX() > Resolution.WIDTH) || (model.getX() < 0) ||
                (model.getY() > Resolution.HEIGHT) || (model.getY() < 0)){
            destroy();
        }


        if (model.getDirection() == Direction.UP) {
            float newPosition = model.getY() + model.getSpeed();
            model.setY(newPosition);
        } else if (model.getDirection() == Direction.DOWN) {
            float newPosition = model.getY() - model.getSpeed();
            model.setY(newPosition);
        } else if (model.getDirection() == Direction.LEFT) {
            float newPosition = model.getX() - model.getSpeed();
            model.setX(newPosition);
        } else if (model.getDirection() == Direction.RIGHT) {
            float newPosition = model.getX() + model.getSpeed();
            model.setX(newPosition);
        }
    }

    public void destroy() {
        screen.destroyBullet(this);
        model.getOwner().bulletDestroyed();
    }
}
