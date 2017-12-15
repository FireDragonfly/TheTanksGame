package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hvitalii.thetanksgame.AssetsHandler;

public class GameScreen implements Screen {

    AssetsHandler assetsHandler;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private float positionY = 0F;
    Sprite tank;
    private int frame = 0;

    public GameScreen(AssetsHandler assetsHandler) {
        this.assetsHandler = assetsHandler;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(colorValue ,colorValue, colorValue, 1);
        TextureAtlas atlas = assetsHandler.getAssetManager().get("TextureAtlases/Tanks/p1.atlas");
        tank = new Sprite(atlas.findRegion("p1", 0));
        tank.setBounds(64,64, 32,32);
        tank.setOriginCenter();
    }

    @Override
    public void render(float delta) {
        frame++;
        positionY += 0.1;
//        tank.setY(positionY);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (frame == 60) {
            tank.setRotation(90);
        } else if (frame == 120) {
            tank.setRotation(0);
            tank.setColor(0.5f, 0.5f, 0.5f, 1);
        } else  if (frame == 300) {
            tank.setRotation(180);
            frame = 0;
        }
        batch.begin();
        tank.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
