package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hvitalii.thetanksgame.Model.Tank;

public class GameScreen implements Screen {

    private Texture tankTexture;
    private SpriteBatch batch;
    private Tank tank;

    private float positionY = 0F;

    @Override
    public void show() {
        batch = new SpriteBatch();
        tankTexture = new Texture(Gdx.files.internal("t1.png"));
        tank = new Tank(tankTexture, 0 ,0, 64, 64);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 ,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        positionY++;
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
        tankTexture.dispose();
        batch.dispose();
    }
}
