package com.hvitalii.thetanksgame;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
//import com.hvitalii.thetanksgame.View.GameTextures;

public class AssetsHandler implements Disposable{
    private AssetManager assetManager;
    //private GameTextures textures;

    public AssetsHandler() {
        assetManager = new AssetManager();
//        textures = new GameTextures();
    }

    public void loadAssets() {
        assetManager.load("TextureAtlases/animation.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/bullets.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/tiles.atlas", TextureAtlas.class);

        assetManager.load("TextureAtlases/Tanks/small.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/Tanks/apc.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/Tanks/medium.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/Tanks/heavy.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/Tanks/p1.atlas", TextureAtlas.class);
        assetManager.load("TextureAtlases/Tanks/p2.atlas", TextureAtlas.class);
        assetManager.finishLoading();

    }


    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

//    public GameTextures getTextures() {
//        return textures;
//    }

//    public void setTextures(GameTextures textures) {
//        this.textures = textures;
//    }

    @Override
    public void dispose() {
//        textures.dispose();
        assetManager.dispose();
    }
}
