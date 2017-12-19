package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.GameController;
import com.hvitalii.thetanksgame.TheTanksGame;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

public class MainMenuScreen extends ScreenAdapter{

    private ResourcesHandler resourcesHandler;
    private TheTanksGame game;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    public MainMenuScreen(ResourcesHandler resourcesHandler, TheTanksGame game) {
        this.resourcesHandler = resourcesHandler;
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        //float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(0 ,0, 0, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        newGame();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void newGame() {
        GameController state = new GameController(resourcesHandler, 1);
        GameScreen gameScreen = new GameScreen(state, game);
        game.setScreen(gameScreen);
    }
}
