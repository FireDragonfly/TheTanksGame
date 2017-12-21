package com.hvitalii.thetanksgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.hvitalii.thetanksgame.Constants.GameConstants.Files;

public class ResourcesHandler implements Disposable{

    private static final String DEFAULT_EXTERNAL_MAPS_FOLDER = Files.EXTERNAL_MAPS_LOCATION;
    private AssetManager assetManager;
    private FileHandle[] maps;
    private FileHandle[] externalMaps;

    private BitmapFont font16;
    private BitmapFont font32;

    public ResourcesHandler() {
        maps = Gdx.files.local(Files.LOCAL_MAPS_LOCATION).list("map");
        if (Gdx.files.isExternalStorageAvailable()) {
            if (Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).isDirectory()) {
                externalMaps = Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).list("map");
            } else {
                Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).mkdirs();
            }
        }
        assetManager = new AssetManager();
        font16 = new BitmapFont(Gdx.files.local(Files.FONTS_LOCATION + "font16.fnt"));
        font32 = new BitmapFont(Gdx.files.local(Files.FONTS_LOCATION + "font32.fnt"));
    }

    public void loadAssets() {
        assetManager.load(Files.ATLASES_LOCATION + Files.ATLAS_NAME, TextureAtlas.class);
        assetManager.load(Files.ATLASES_LOCATION + Files.DEBUGGING_ATLAS_NAME, TextureAtlas.class);
        assetManager.finishLoading();
    }


    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public FileHandle[] getMaps() {
        return maps;
    }

    public FileHandle[] getExternalMaps() {
        return externalMaps;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        font16.dispose();
        font32.dispose();
    }
}
