package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.*;
import com.hvitalii.thetanksgame.Constants.GameConstants;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
import com.hvitalii.thetanksgame.MyOwnUi.MOButton;
import com.hvitalii.thetanksgame.MyOwnUi.MOLabel;
import com.hvitalii.thetanksgame.MyOwnUi.MOLabel.*;

public class StatisticScreen extends ScreenAdapter{

    private ResourcesHandler resourcesHandler;
    private TheTanksGame game;
    private Statistic statistic;
    private GameController gameController;
    public final SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private TextureAtlas atlas;
    private TextureRegion[] botTextures;

    private String[] players;
    private MOLabel hiScore;
    private MOLabel hiScoreNumber;
    private MOLabel stage;
    private MOLabel player;
    private MOLabel botNumber;
    private MOLabel player_score;
    private MOButton gameOver;
    private MOButton next;
    private MOButton random;

    public StatisticScreen(TheTanksGame game, Statistic statistic, GameController gameController) {
        resourcesHandler = game.getResourcesHandler();
        this.game = game;
        this.statistic = statistic;
        this.gameController = gameController;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        initUi();
    }

    @Override
    public void show() {
        super.show();
        Gdx.gl.glClearColor(0 ,0, 0, 1);

        resourcesHandler.updateExternalMaps();

        Array<Player> players = gameController.getPlayers();
        for (int i = 0; i < players.size; i++) {
            statistic.getPlayersScores()[i] = players.get(i).getScore();
            if (statistic.getPlayersScores()[i] > statistic.getHiScore()) {
                statistic.setHiScore(statistic.getPlayersScores()[i]);
            }
        }
        statistic.write();
        initUi();

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawUi();
        batch.end();

        if (gameController.hasAlivePlayer() && gameController.getStageState().isEagleAlive()) {
            if (next.justTouched()) {
                nextState(false);
            } else if (random.justTouched()) {
                nextState(true);
            }
        }
        if (gameOver.justTouched()) {
            exit();
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
        hiScoreNumber.dispose();
        stage.dispose();
        player.dispose();
        botNumber.dispose();
        player_score.dispose();
        gameOver.dispose();
        next.dispose();
        random.dispose();
        atlas.dispose();
    }

    private void nextState(boolean randomMap) {
        gameController.prepareNextStage(randomMap);
        if (!gameController.isPreparingSuccess()) {
            exit();
            return;
        }
        GameScreen gameScreen = new GameScreen(gameController, game, statistic);
        game.setScreen(gameScreen);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    private void initUi() {
        botTextures = new TextureRegion[4];
        atlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.ATLAS_NAME, TextureAtlas.class);
        for (int i = 0; i < botTextures.length; i++) {
            botTextures[i] = atlas.findRegion(GameConstants.BOT_TYPES_NAMES[i], 0);
        }

        hiScore = new MOLabel(resourcesHandler.font32,"HI SCORE");
        hiScoreNumber = new MOLabel(resourcesHandler.font32,"" + statistic.getHiScore());
        stage = new MOLabel(resourcesHandler.font32,"STAGE  " + gameController.getStage());

        player = new MOLabel(resourcesHandler.font32);
        botNumber = new MOLabel(resourcesHandler.font32);
        player_score = new MOLabel(resourcesHandler.font32);
        next = new MOButton(resourcesHandler.font32, "NEXT MAP");
        random = new MOButton(resourcesHandler.font32, "RANDOM MAP");

        //set Positions
        hiScore.setPosition(Resolution.SCREEN_WIDTH / 2, 16 * 12, Align.right);
        hiScoreNumber.setPosition(Resolution.SCREEN_WIDTH / 2 + 16, 16 * 12, Align.left);
        stage.setPosition(Resolution.SCREEN_WIDTH / 2, 16 * 11, Align.center);
        next.setPosition(Resolution.SCREEN_WIDTH / 2 + 16, 16, Align.left);
        random.setPosition(Resolution.SCREEN_WIDTH / 2 - 16, 16, Align.right);

        // set Scales
        hiScore.setScale(0.32f);
        hiScoreNumber.setScale(0.32f);
        stage.setScale(0.35f);
        player.setScale(0.25f);
        botNumber.setScale(0.25f);
        player_score.setScale(0.25f);
        botNumber.setScale(0.25f);
        next.setScale(0.25f);
        random.setScale(0.25f);

        // set Colors
        hiScore.setColor(UiColors.TTG_RED);
        hiScoreNumber.setColor(UiColors.TTG_ORANGE);
        player_score.setColor(UiColors.TTG_ORANGE);
        player.setColor(UiColors.TTG_RED);

        next.setColor(UiColors.TTG_ORANGE);
        next.setHoverColor(UiColors.TTG_RED);
        random.setColor(UiColors.TTG_GREY);
        random.setHoverColor(UiColors.TTG_RED);

        gameOver = new MOButton(resourcesHandler.font32, "EXIT");
        if (gameController.hasAlivePlayer() && gameController.getStageState().isEagleAlive()) {
            gameOver.setPosition(Resolution.SCREEN_WIDTH - 16, 16, Align.right);
            gameOver.setScale(0.25f);
        } else {
            gameOver.setPosition(Resolution.SCREEN_WIDTH / 2, 16, Align.center);
            gameOver.setScale(0.35f);
        }
        gameOver.setColor(UiColors.TTG_GREY);
        gameOver.setHoverColor(UiColors.TTG_RED);
    }

    private void drawUi() {
        hiScore.draw(batch);
        hiScoreNumber.draw(batch);
        stage.draw(batch);

        for (int i = 0; i < gameController.getPlayers().size; i++) {
            player.setPosition(8, 8 * (15 - 3 * i), Align.left);
            player.setText(i + 1 +" P");
            player_score.setPosition(Resolution.SCREEN_WIDTH - 8, 8 * (15 - 3 * i), Align.right);
            player_score.setText("" + statistic.getPlayersScores()[i]);
            int[] botDestroyed = gameController.getPlayers().get(i).getStageBotsDestroyed();
            for (int j = 0; j < botDestroyed.length; j++) {
                botNumber.setPosition(40 + (32 * j), 8 * (15 - 3 * i), Align.left);
                botNumber.setText("" + botDestroyed[j]);
                botNumber.draw(batch);
            }
            player.draw(batch);
            player_score.draw(batch);
        }

        gameOver.draw(batch);
        if (gameController.hasAlivePlayer() && gameController.getStageState().isEagleAlive()) {
            next.draw(batch);
            random.draw(batch);
        }

        for (int i = 0; i < botTextures.length; i++) {
            batch.draw(botTextures[i], 40 + (32 * i), 8 * 17, Size.TANK, Size.TANK);
        }


    }

    private void exit() {
        MainMenuScreen menuScreen = new MainMenuScreen(game.getResourcesHandler(), game, statistic);
        game.setScreen(menuScreen);
    }
}
