package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Statistic;
import com.hvitalii.thetanksgame.TheTanksGame;
import com.hvitalii.thetanksgame.View.MyOwnButton;
import com.hvitalii.thetanksgame.View.MyOwnLabel;
import com.hvitalii.thetanksgame.View.MyOwnLabel.*;

public class GameScreen implements Screen {

    TheTanksGame game;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private GameController gameController;
    private Statistic statistic;

    private MyOwnButton pause;
    private MyOwnButton exit;
    private MyOwnLabel gameOver;
    private boolean isPause;


    public GameScreen(GameController gameController, TheTanksGame game, Statistic statistic) {
        this.gameController = gameController;
        this.game = game;
        this.statistic = statistic;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        Gdx.gl.glClearColor(0 ,0, 0, 1);
        isPause = false;
        initUi();
    }

    @Override
    public void render(float delta) {
        if (gameController.isTimeToExit()) {
            exit();
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!isPause) {

            gameController.update();

            batch.begin();
            gameController.draw(batch);
            pause.draw(batch);
            batch.end();
            if (!gameController.getStageState().isEagleAlive() || !gameController.hasAlivePlayer()) {
                fillGrayScreen();
                batch.begin();
                gameOver.draw(batch);
                batch.end();
            } else {
                if (pause.justTouched()) {
                    isPause = true;
                    pause.setText("PLAY");
                }
            }
        } else {
            batch.begin();
            gameController.draw(batch);
            batch.end();

            fillGrayScreen();

            batch.begin();
            pause.draw(batch);
            exit.draw(batch);
            batch.end();

            if (exit.justTouched()) {
                exit();
            } else if (pause.justTouched()) {
                isPause = false;
                pause.setText("PAUSE");
            }
        }

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

    private void fillGrayScreen() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.25f, 0.25f, 0.25f, 0.7f);
        shapeRenderer.rect(0, 0, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void initUi() {
        pause = new MyOwnButton(game.getResourcesHandler().font32, "PAUSE");
        pause.setTextAlignment(Align.center);
        pause.setPosition(Resolution.SCREEN_WIDTH - 4 , 8, Align.right);
        pause.setScale(0.2f);
        pause.setColor(Color.BLACK);
        pause.setHoverColor(Color.WHITE);

        exit = new MyOwnButton(game.getResourcesHandler().font32, "EXIT");
        exit.setTextAlignment(Align.center);
        exit.setScale(0.25f);
        exit.setPosition(Resolution.SCREEN_WIDTH / 2, 16, Align.center);
        exit.setColor(Color.ORANGE);
        exit.setHoverColor(UiColors.TTG_RED);

        gameOver = new MyOwnLabel(game.getResourcesHandler().font32, "GAME\nOVER");
        gameOver.setTextAlignment(Align.center);
        gameOver.setScale(0.5f);
        gameOver.setPosition(Resolution.SCREEN_WIDTH / 2, (Resolution.SCREEN_HEIGHT / 2) - (gameOver.getHeight() / 2), Align.center);
        gameOver.setColor(UiColors.TTG_RED);
    }

    private void exit() {
        StatisticScreen statisticScreen = new StatisticScreen(game, statistic, gameController);
        game.setScreen(statisticScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
