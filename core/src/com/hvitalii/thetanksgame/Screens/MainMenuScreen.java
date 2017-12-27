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
import com.hvitalii.thetanksgame.MyOwn.MOButton;
import com.hvitalii.thetanksgame.MyOwn.MOLabel;
import com.hvitalii.thetanksgame.MyOwn.MOLabel.Align;

public class MainMenuScreen extends ScreenAdapter{

    private ResourcesHandler resourcesHandler;
    private TheTanksGame game;
    private Statistic statistic;
    public final SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ConstructionScreen constructionScreen;

    private MOLabel hiScore;
    private MOLabel title;
    private MOButton onePlayer;
    private MOButton twoPlayers;
    private MOButton threePlayers;
    private MOButton fourPlayers;
    private MOButton construction;
    private MOButton exit;

    private int command;

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
        command = 0;
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
            command = 1;
        } else if (twoPlayers.justTouched()) {
            command = 2;
        } else if (threePlayers.justTouched()) {
            command = 3;
        } else if (fourPlayers.justTouched()) {
            command = 4;
        } else if (construction.justTouched()) {
            command = 5;
        } else if (exit.justTouched()) {
            command = -1;
        } else {
            command = 0;
        }

        switch (command) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (!newGame(command)) {
                    title.setText("MAPS IS\nNOT FOUNDED");
                    title.setScale(0.7f);
                }
                break;
            case 5:
                if (constructionScreen == null) {
                    constructionScreen = new ConstructionScreen(resourcesHandler, game, this);
                    game.setScreen(constructionScreen);
                } else {
                    game.setScreen(constructionScreen);
                }
                break;
            case -1:
                Gdx.app.exit();
                break;
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

    private boolean newGame(int playersNumber) {
        GameController gameController = new GameController(resourcesHandler, playersNumber);
        if (!gameController.isPreparingSuccess()) {
            return false;
        }
        GameScreen gameScreen = new GameScreen(gameController, game, statistic);
        game.setScreen(gameScreen);
        return true;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    private void initUi() {
        hiScore = new MOLabel(resourcesHandler.font32,"HI SCORE >    " + statistic.getHiScore());
        title = new MOLabel(resourcesHandler.font32,"THE TANKS\nGAME");
        onePlayer = new MOButton(resourcesHandler.font32, "1 PLAYER");
        twoPlayers = new MOButton(resourcesHandler.font32, "2 PLAYERS");
        threePlayers = new MOButton(resourcesHandler.font32, "3 PLAYERS");
        fourPlayers = new MOButton(resourcesHandler.font32, "4 PLAYERS");
        construction = new MOButton(resourcesHandler.font32, "CONSTRUCTION");
        exit = new MOButton(resourcesHandler.font32, "EXIT");


        onePlayer.setTextAlignment(Align.center);
        twoPlayers.setTextAlignment(Align.center);
        threePlayers.setTextAlignment(Align.center);
        fourPlayers.setTextAlignment(Align.center);
        construction.setTextAlignment(Align.center);
        hiScore.setTextAlignment(Align.center);
        title.setTextAlignment(Align.center);
        exit.setTextAlignment(Align.center);

        hiScore.setPosition(8, 16 * 13, Align.left);
        title.setPosition(Resolution.SCREEN_WIDTH/2, 16 * 8, Align.center);
        onePlayer.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 12, Align.center);
        twoPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 10, Align.center);
        threePlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 8, Align.center);
        fourPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 6, Align.center);
        construction.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 4, Align.center);
        exit.setPosition(Resolution.SCREEN_WIDTH - 16, 16, Align.right);

        hiScore.setScale(0.25f);
        title.setScale(1);
        onePlayer.setScale(0.25f);
        twoPlayers.setScale(0.25f);
        threePlayers.setScale(0.25f);
        fourPlayers.setScale(0.25f);
        construction.setScale(0.25f);
        exit.setScale(0.25f);

        hiScore.setColor(Color.ORANGE);
        title.setColor(UiColors.TTG_RED);

        onePlayer.setColor(UiColors.TTG_GREY);
        twoPlayers.setColor(UiColors.TTG_GREY);
        threePlayers.setColor(UiColors.TTG_GREY);
        fourPlayers.setColor(UiColors.TTG_GREY);
        construction.setColor(UiColors.TTG_GREY);

        onePlayer.setHoverColor(Color.WHITE);
        twoPlayers.setHoverColor(Color.WHITE);
        threePlayers.setHoverColor(Color.WHITE);
        fourPlayers.setHoverColor(Color.WHITE);
        construction.setHoverColor(Color.WHITE);

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
        construction.draw(batch);
        exit.draw(batch);
    }
}
