package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BulletModel;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.View.BulletView;

public class BulletController implements Controller{

    private GameScreen screen;
    private BulletModel model;
    private BulletView view;

    public BulletController(GameScreen screen, Rectangle rectangle, TextureAtlas atlas, int bulletType, float direction, TankController owner, int ownerType) {
        this.screen = screen;
        model = new BulletModel(rectangle, bulletType, direction, owner, ownerType);
        view = new BulletView(atlas, model);
    }

    @Override
    public void update() {
        float direction = model.getDirection();

        move();

//        if (model.getOwner() != null) {
//            if (model.getOwner().isDestructed()) {
//                model.setOwner(null);
//            }
//        }

        if ((model.getX() > Resolution.SCREEN_WIDTH) || (model.getX() < 0) ||
                (model.getY() > Resolution.SCREEN_HEIGHT) || (model.getY() < 0)){
            destruct();
        }

        Vector2 dot1 = new Vector2(model.x, model.y);
        Vector2 dot2 = new Vector2(model.x + Size.BULLET, model.y);
        Vector2 dot3 = new Vector2(model.x, model.y + Size.BULLET);
        Vector2 dot4 = new Vector2(model.x + Size.BULLET, model.y + Size.BULLET);

        if (direction == Direction.UP) {
            checkCollision(dot3, dot4);
        } else if (direction == Direction.DOWN) {
            checkCollision(dot1, dot2);
        } else if (direction == Direction.LEFT) {
            checkCollision(dot1, dot3);
        } else if (direction == Direction.RIGHT) {
            checkCollision(dot2, dot4);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        view.draw(batch);
    }

    public int getOwnerType() {
        return model.getOwnerType();
    }

    public void destruct() {
        screen.destructBullet(this);
        model.getOwner().bulletDestroyed();
    }

    private void checkCollision(Vector2 dot1, Vector2 dot2) {
        BattleFieldController battleField = screen.getBattleField();
        if (battleField.hitTank(this, dot1.x, dot1.y)) {
            destruct();
        } else if (battleField.hitTank(this, dot2.x, dot2.y)){
            destruct();
        } else {
            boolean hit1 = battleField.hitBlock(this, dot1.x, dot1.y);
            boolean hit2 = battleField.hitBlock(this, dot2.x, dot2.y);
            if (hit1 || hit2) {
                destruct();
            }
        }
    }

    private void move() {
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
}
