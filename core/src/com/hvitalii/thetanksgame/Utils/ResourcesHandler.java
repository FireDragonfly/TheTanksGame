package com.hvitalii.thetanksgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class ResourcesHandler implements Disposable{

    private static final String DEFAULT_EXTERNAL_MAPS_FOLDER = "The Tanks Game/maps/";
    private AssetManager assetManager;
    private FileHandle[] maps;
    private FileHandle[] externalMaps;

    public ResourcesHandler() {
        maps = Gdx.files.local("Maps/").list("map");
        if (Gdx.files.isExternalStorageAvailable()) {
            if (Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).isDirectory()) {
                externalMaps = Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).list("map");
            } else {
                Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).mkdirs();
            }
        }
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

    public FileHandle[] getMaps() {
        return maps;
    }

    public FileHandle[] getExternalMaps() {
        return externalMaps;
    }
}
