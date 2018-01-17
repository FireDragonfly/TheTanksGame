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
import com.hvitalii.thetanksgame.MyOwnUi.MOButton;
import com.hvitalii.thetanksgame.MyOwnUi.MOLabel;
import com.hvitalii.thetanksgame.MyOwnUi.MOLabel.Align;

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
    private MOLabel titleShadow;
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
        resourcesHandler.updateExternalMaps();
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
        constructionScreen.dispose();
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
        hiScore.setTextAlignment(Align.center);
        hiScore.setPosition(8, 16 * 13, Align.left);
        hiScore.setScale(0.25f);
        hiScore.setColor(Color.ORANGE);

        titleShadow = new MOLabel(resourcesHandler.font32,"THE TANKS\nGAME");
        titleShadow.setTextAlignment(Align.center);
        titleShadow.setPosition(Resolution.SCREEN_WIDTH/2 - 2, 16 * 8 - 1, Align.center);
        titleShadow.setScale(1);
        titleShadow.setColor(Color.WHITE);

        title = new MOLabel(resourcesHandler.font32,"THE TANKS\nGAME");
        title.setTextAlignment(Align.center);
        title.setPosition(Resolution.SCREEN_WIDTH/2, 16 * 8, Align.center);
        title.setScale(1);
        title.setColor(UiColors.TTG_RED);

        onePlayer = new MOButton(resourcesHandler.font32, "1 PLAYER");
        onePlayer.setTextAlignment(Align.center);
        onePlayer.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 12, Align.center);
        onePlayer.setScale(0.25f);
        onePlayer.setColor(UiColors.TTG_GREY);
        onePlayer.setHoverColor(Color.WHITE);

        twoPlayers = new MOButton(resourcesHandler.font32, "2 PLAYERS");
        twoPlayers.setTextAlignment(Align.center);
        twoPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 10, Align.center);
        twoPlayers.setScale(0.25f);
        twoPlayers.setColor(UiColors.TTG_GREY);
        twoPlayers.setHoverColor(Color.WHITE);

        threePlayers = new MOButton(resourcesHandler.font32, "3 PLAYERS");
        threePlayers.setTextAlignment(Align.center);
        threePlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 8, Align.center);
        threePlayers.setScale(0.25f);
        threePlayers.setColor(UiColors.TTG_GREY);
        threePlayers.setHoverColor(Color.WHITE);

        fourPlayers = new MOButton(resourcesHandler.font32, "4 PLAYERS");
        fourPlayers.setTextAlignment(Align.center);
        fourPlayers.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 6, Align.center);
        fourPlayers.setScale(0.25f);
        fourPlayers.setColor(UiColors.TTG_GREY);
        fourPlayers.setHoverColor(Color.WHITE);


        construction = new MOButton(resourcesHandler.font32, "CONSTRUCTION");
        construction.setTextAlignment(Align.center);
        construction.setPosition(Resolution.SCREEN_WIDTH/2, 8 * 4, Align.center);
        construction.setScale(0.25f);
        construction.setColor(UiColors.TTG_GREY);
        construction.setHoverColor(Color.WHITE);

        exit = new MOButton(resourcesHandler.font32, "EXIT");
        exit.setTextAlignment(Align.center);
        exit.setPosition(Resolution.SCREEN_WIDTH - 16, 16, Align.right);
        exit.setScale(0.25f);
        exit.setColor(Color.ORANGE);
        exit.setHoverColor(UiColors.TTG_RED);
    }

    private void drawUi() {
        hiScore.draw(batch);
        titleShadow.draw(batch);
        title.draw(batch);
        onePlayer.draw(batch);
        twoPlayers.draw(batch);
        threePlayers.draw(batch);
        fourPlayers.draw(batch);
        construction.draw(batch);
        exit.draw(batch);
    }
}
