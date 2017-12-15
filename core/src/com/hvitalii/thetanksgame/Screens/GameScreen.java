package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hvitalii.thetanksgame.Utils.AssetsHandler;

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

    public int getFrame() {
        return frame;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(colorValue ,colorValue, colorValue, 1);
    }

    @Override
    public void render(float delta) {
        frame++;
        positionY += 0.1;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        //tank.draw(batch);
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
