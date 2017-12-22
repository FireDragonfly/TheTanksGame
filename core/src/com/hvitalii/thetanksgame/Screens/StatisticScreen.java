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
import com.hvitalii.thetanksgame.View.MyOwnButton;
import com.hvitalii.thetanksgame.View.MyOwnLabel;
import com.hvitalii.thetanksgame.View.MyOwnLabel.*;

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
    private MyOwnLabel hiScore;
    private MyOwnLabel hiScoreNumber;
    private MyOwnLabel stage;
    private MyOwnLabel player;
    private MyOwnLabel botNumber;
//    private MyOwnLabel player_3;
//    private MyOwnLabel player_4;
    private MyOwnLabel player_score;
//    private MyOwnLabel player_2_score;
//    private MyOwnLabel player_3_score;
//    private MyOwnLabel player_4_score;
//    private MyOwnLabel arrow;
    private MyOwnButton exit;
    private MyOwnButton next;
    private MyOwnButton random;

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

        Array<Player> players = gameController.getPlayers();
        for (int i = 0; i < players.size; i++) {
            statistic.getPlayersScores()[i] = players.get(i).getScore();
            if (statistic.getPlayersScores()[i] > statistic.getHiScore()) {
                statistic.setHiScore(statistic.getPlayersScores()[i]);
            }
        }
        initUi();
        render(30);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        drawUi();
        batch.end();
        if (delta >= 25) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (exit.isPressed()) {
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
        exit.dispose();
        next.dispose();
        random.dispose();
        atlas.dispose();
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
        botTextures = new TextureRegion[4];
        atlas = resourcesHandler.getAssetManager().get(Files.ATLASES_LOCATION + Files.ATLAS_NAME, TextureAtlas.class);
        for (int i = 0; i < botTextures.length; i++) {
            botTextures[i] = atlas.findRegion(GameConstants.BOT_TYPES_NAMES[i], 0);
        }

        hiScore = new MyOwnLabel(resourcesHandler.font32,"HI SCORE");
        hiScoreNumber = new MyOwnLabel(resourcesHandler.font32,"" + statistic.getHiScore());
        stage = new MyOwnLabel(resourcesHandler.font32,"STAGE  " + gameController.getStage());

        player = new MyOwnLabel(resourcesHandler.font32);
        botNumber = new MyOwnLabel(resourcesHandler.font32);
        player_score = new MyOwnLabel(resourcesHandler.font32);

        exit = new MyOwnButton(resourcesHandler.font32, "EXIT");
        next = new MyOwnButton(resourcesHandler.font32, "NEXT MAP");
        random = new MyOwnButton(resourcesHandler.font32, "RANDOM MAP");

        //set Positions
        hiScore.setPosition(Resolution.SCREEN_WIDTH / 2, 16 * 12, Align.right);
        hiScoreNumber.setPosition(Resolution.SCREEN_WIDTH / 2 + 16, 16 * 12, Align.left);
        stage.setPosition(Resolution.SCREEN_WIDTH / 2, 16 * 10, Align.center);
        exit.setPosition(Resolution.SCREEN_WIDTH - 16, 16, Align.right);
        next.setPosition(Resolution.SCREEN_WIDTH / 2 + 16, 16, Align.left);
        random.setPosition(Resolution.SCREEN_WIDTH / 2 - 16, 16, Align.right);

        // set Scales
        hiScore.setScale(0.3f);
        hiScoreNumber.setScale(0.3f);
        stage.setScale(0.3f);
        player.setScale(0.25f);
        botNumber.setScale(0.25f);
        player_score.setScale(0.25f);
        botNumber.setScale(0.25f);
        exit.setScale(0.25f);
        next.setScale(0.25f);
        random.setScale(0.25f);

        // set Colors
        hiScore.setColor(UiColors.TTG_RED);
        hiScoreNumber.setColor(UiColors.TTG_ORANGE);

        player_score.setColor(UiColors.TTG_ORANGE);
        player.setColor(UiColors.TTG_RED);

        exit.setColor(UiColors.TTG_GREY);
        exit.setHoverColor(UiColors.TTG_RED);
        next.setColor(UiColors.TTG_ORANGE);
        next.setHoverColor(UiColors.TTG_RED);
        random.setColor(UiColors.TTG_GREY);
        random.setHoverColor(UiColors.TTG_RED);


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

        exit.draw(batch);
//        next.draw(batch);
//        random.draw(batch);

        for (int i = 0; i < botTextures.length; i++) {
            batch.draw(botTextures[i], 40 + (32 * i), 8 * 17, Size.TANK, Size.TANK);
        }


    }

    private void exit() {
        MainMenuScreen menuScreen = new MainMenuScreen(game.getResourcesHandler(), game, statistic);
        game.setScreen(menuScreen);
    }
}
