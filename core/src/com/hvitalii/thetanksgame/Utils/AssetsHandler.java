package com.hvitalii.thetanksgame.Utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class AssetsHandler implements Disposable{
    private AssetManager assetManager;

    public AssetsHandler() {
        assetManager = new AssetManager();
    }

    public void loadAssets() {
        assetManager.load("atlas.atlas", TextureAtlas.class);
        assetManager.load("debugging_atlas.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }


    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
