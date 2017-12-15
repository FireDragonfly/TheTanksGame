package com.hvitalii.thetanksgame.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hvitalii.thetanksgame.Controller.BulletController;
import com.hvitalii.thetanksgame.Controller.PlayerTankController;
import com.hvitalii.thetanksgame.Controller.Controller;
import com.hvitalii.thetanksgame.Controller.TankController;
import com.hvitalii.thetanksgame.Utils.AssetsHandler;

public class GameScreen implements Screen {

    private AssetsHandler assetsHandler;
    private TextureAtlas textureAtlas;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private long frame;
    private Controller tank;
    private Array<BulletController> bullets;


    public GameScreen(AssetsHandler assetsHandler) {
        this.assetsHandler = assetsHandler;
    }

    public long getFrame() {
        return frame;
    }

    @Override
    public void show() {
        bullets = new Array<BulletController>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 208, 224);
        //viewport = new FitViewport(416, 448, camera);

        batch = new SpriteBatch();
        textureAtlas = assetsHandler.getAssetManager().get("atlas.atlas");
        //textureAtlas = assetsHandler.getAssetManager().get("debugging_atlas.atlas");
        frame = 0;
        float colorValue = 0.00390625f * 63;
        Gdx.gl.glClearColor(colorValue ,colorValue, colorValue, 1);
        Rectangle rectangle = new Rectangle(0, 0, 16, 16);
        tank = new PlayerTankController(this, rectangle, textureAtlas, 0, 3);
    }

    @Override
    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        frame++;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update();
        }
        tank.update();
        batch.begin();
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).draw(batch);
        }
        tank.draw(batch);
        batch.end();
    }

    public void spawnBullet(BulletController bullet) {
        bullets.add(bullet);
    }

    public void destroyBullet(BulletController bullet) {
        bullets.removeValue(bullet, true);
    }

    @Override
    public void resize(int width, int height) {
        //viewport.update(width, height);
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
        assetsHandler.dispose();
        batch.dispose();
    }
}
