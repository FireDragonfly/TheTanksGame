package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.TheTanksGame;

public class GameScreen implements Screen {

    TheTanksGame game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    //private FPSLogger logger;

    private GameController state;


    public GameScreen(GameController state, TheTanksGame game) {
        this.state = state;
        this.game = game;
    }

    @Override
    public void show() {

        //logger = new FPSLogger();

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        //float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(0 ,0, 0, 1);

    }

    @Override
    public void render(float delta) {
        if (state.isTimeToExit()) {
            Gdx.app.exit();
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        state.update();
        batch.begin();
        state.draw(batch);
        batch.end();

        //logger.log();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
