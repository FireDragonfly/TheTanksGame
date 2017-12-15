package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;

public class PlayerTankView{

    private TextureAtlas atlas;
    private Sprite[] sprites;
    private PlayerTankModel tank;

    public PlayerTankView(TextureAtlas atlas, PlayerTankModel tank) {
        sprites = new Sprite[2];
        this.atlas = atlas;
        this.tank = tank;
        for (int i = 0; i < 2; i++) {
            sprites[i] = new Sprite(atlas.findRegion(ObjectsConstants.PLAYER_TANKS_NAMES[tank.getLevel()], i));
            sprites[i].setColor(Colors.PLAYERS_COLORS[tank.getPlayerNumber()]);
            sprites[i].setOriginCenter();
        }
    }

    private void updateSprites() {
        if (tank.isDirectionChanged()) {
            for (int i = 0; i < 2; i++) {
                sprites[i].setRotation(tank.getDirection());
            }
        }
        for (int i = 0; i < 2; i++) {
            sprites[0].setX(tank.getX());
            sprites[0].setY(tank.getY());
        }
    }

    public void draw(SpriteBatch batch) {
        updateSprites();
        sprites[tank.getAnimationFrame()].draw(batch);

    }

    public void levelChanged() {
        for (int i = 0; i < 2; i++) {
            sprites[i].setRegion(atlas.findRegion(ObjectsConstants.PLAYER_TANKS_NAMES[tank.getLevel()], i));
        }
    }
}
