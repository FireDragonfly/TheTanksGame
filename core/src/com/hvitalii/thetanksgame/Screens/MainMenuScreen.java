package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.Statistic;
import com.hvitalii.thetanksgame.TheTanksGame;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
import com.hvitalii.thetanksgame.View.MyOwnButton;
import com.hvitalii.thetanksgame.View.MyOwnLabel;
import com.hvitalii.thetanksgame.View.MyOwnLabel.Align;

public class MainMenuScreen extends ScreenAdapter{

    private ResourcesHandler resourcesHandler;
    private TheTanksGame game;
    private Statistic statistic;
    public final SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private MyOwnLabel hiScore;
    private MyOwnLabel title;
    private MyOwnButton onePlayer;
    private MyOwnButton twoPlayers;
    private MyOwnButton threePlayers;
    private MyOwnButton fourPlayers;
    private MyOwnButton exit;

    public MainMenuScreen(ResourcesHandler resourcesHandler, TheTanksGame game, Statistic statistic) {
        this.resourcesHandler = resourcesHandler;
        this.game = game;
        this.statistic = statistic;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        initUi();
    }

    @Override
    public void show() {
        super.show();
        //float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(0 ,0, 0, 1);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        batch.begin();
        drawUi();
        batch.end();

        if (onePlayer.justTouched()){
            newGame(1);
        } else if (twoPlayers.justTouched()) {
            newGame(2);
        } else if (threePlayers.justTouched()) {
            newGame(3);
        } else if (fourPlayers.justTouched()) {
            newGame(4);
        } else if (exit.justTouched()) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        hiScore.dispose();
        title.dispose();
        onePlayer.dispose();
        twoPlayers.dispose();
        threePlayers.dispose();
        fourPlayers.dispose();
        exit.dispose();
    }

    private void newGame(int playerNumber) {
        GameController state = new GameController(resourcesHandler, playerNumber);
        GameScreen gameScreen = new GameScreen(state, game, statistic);
        game.setScreen(gameScreen);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    private void initUi() {
        hiScore = new MyOwnLabel(resourcesHandler.font32,"HI SCORE >    " + statistic.getHiScore());
        title = new MyOwnLabel(resourcesHandler.font32,"THE TANKS\nGAME");
        onePlayer = new MyOwnButton(resourcesHandler.font32, "1 PLAYER");
        twoPlayers = new MyOwnButton(resourcesHandler.font32, "2 PLAYERS");
        threePlayers = new MyOwnButton(resourcesHandler.font32, "3 PLAYERS");
        fourPlayers = new MyOwnButton(resourcesHandler.font32, "4 PLAYERS");
        exit = new MyOwnButton(resourcesHandler.font32, "EXIT");


        onePlayer.setTextAlignment(Align.center);
        twoPlayers.setTextAlignment(Align.center);
        threePlayers.setTextAlignment(Align.center);
        fourPlayers.setTextAlignment(Align.center);
        hiScore.setTextAlignment(Align.center);
        title.setTextAlignment(Align.center);
        exit.setTextAlignment(Align.center);

        hiScore.setPosition(8, 16 * 13, Align.left);
        title.setPosition(Resolution.SCREEN_WIDTH/2, 16 * 8, Align.center);
        onePlayer.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 12, Align.center);
        twoPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 10, Align.center);
        threePlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 8, Align.center);
        fourPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 6, Align.center);
        exit.setPosition(Resolution.SCREEN_WIDTH - 16, 16, Align.right);

        hiScore.setScale(0.25f);
        title.setScale(1);
        onePlayer.setScale(0.25f);
        twoPlayers.setScale(0.25f);
        threePlayers.setScale(0.25f);
        fourPlayers.setScale(0.25f);
        exit.setScale(0.25f);

        hiScore.setColor(Color.ORANGE);
        title.setColor(UiColors.TTG_RED);

        onePlayer.setColor(UiColors.TTG_GREY);
        twoPlayers.setColor(UiColors.TTG_GREY);
        threePlayers.setColor(UiColors.TTG_GREY);
        fourPlayers.setColor(UiColors.TTG_GREY);

        onePlayer.setHoverColor(Color.WHITE);
        twoPlayers.setHoverColor(Color.WHITE);
        threePlayers.setHoverColor(Color.WHITE);
        fourPlayers.setHoverColor(Color.WHITE);

        exit.setColor(Color.ORANGE);
        exit.setHoverColor(UiColors.TTG_RED);
    }

    private void drawUi() {
        hiScore.draw(batch);
        title.draw(batch);
        onePlayer.draw(batch);
        twoPlayers.draw(batch);
        threePlayers.draw(batch);
        fourPlayers.draw(batch);
        exit.draw(batch);
    }
}
