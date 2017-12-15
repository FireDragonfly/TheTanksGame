package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Model.BulletModel;

public class BulletView {

//    private TextureAtlas atlas;
    private Sprite sprite;
    private BulletModel model;

    public BulletView(TextureAtlas atlas, BulletModel model) {
//        this.atlas = atlas;
        this.model = model;
        sprite = new Sprite(atlas.findRegion("bullet", model.getBulletType()));
        sprite.setBounds(model.x, model.y, model.width, model.height);
        sprite.setOriginCenter();
        sprite.setRotation(model.getDirection());
    }

    public void draw(SpriteBatch batch) {
        updateSprite();
        sprite.draw(batch);
    }

    private void updateSprite(){
        sprite.setX(model.x);
        sprite.setY(model.y);
    }
}
