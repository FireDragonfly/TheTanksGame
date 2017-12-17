package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Controller.*;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

public class GameScreen implements Screen {

    private ResourcesHandler resourcesHandler;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private long frame;
    private Array<TankController> tanks;
    private Array<BulletController> bullets;
    private BattleFieldController battleField;
    private FPSLogger logger;


    public GameScreen(ResourcesHandler resourcesHandler) {
        this.resourcesHandler = resourcesHandler;
    }

    public long getFrame() {
        return frame;
    }

    @Override
    public void show() {

        logger = new FPSLogger();

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
        viewport = new FitViewport(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, camera);
        textureAtlas = resourcesHandler.getAssetManager().get("atlas.atlas");
        //textureAtlas = resourcesHandler.getAssetManager().get("debugging_atlas.atlas");
        //float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(0 ,0, 0, 1);

        tanks = new Array<TankController>();
        bullets = new Array<BulletController>();
        frame = 0;

        battleField = new BattleFieldController(this, textureAtlas);
        battleField.setMap(resourcesHandler.getMaps()[0]);

        Rectangle rectangle = new Rectangle(16, 8, 16, 16);
        tanks.add(new BotTankController(this, rectangle, textureAtlas, BotTypes.HEAVY, -1));
        rectangle.setPosition(48, 8);
        tanks.add(new PlayerTankController(this, rectangle, textureAtlas, 0, 0));

    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        frame++;

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < tanks.size; i++) { //Update tanks
            tanks.get(i).update();
        }
        battleField.update();
        for (int i = 0; i < bullets.size; i++) { //Update bullets
            bullets.get(i).update();
        }

        batch.begin();
        battleField.drawBottomLayers(batch);
        for (int i = 0; i < tanks.size; i++) { //Draw tanks
            tanks.get(i).draw(batch);
        }
        for (int i = 0; i < bullets.size; i++) { //Draw bullets
            bullets.get(i).draw(batch);
        }
        battleField.drawTopLayers(batch);
        batch.end();
        logger.log();
    }

    public void spawnBullet(BulletController bullet) {
        bullets.add(bullet);
    }

    public void destructBullet(BulletController bullet) {
        bullets.removeValue(bullet, true);
    }

    public void destructTank(TankController tank) {
        tanks.removeValue(tank, true);
    }

    public BattleFieldController getBattleField() {
        return battleField;
    }

    public Array<TankController> getTanks() {
        return tanks;
    }

    public Array<BulletController> getBullets() {
        return bullets;
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
        resourcesHandler.dispose();
        batch.dispose();
    }
}
