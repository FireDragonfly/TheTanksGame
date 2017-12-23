package com.hvitalii.thetanksgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.hvitalii.thetanksgame.Constants.GameConstants.Files;

public class ResourcesHandler implements Disposable{

    private static final String DEFAULT_EXTERNAL_MAPS_FOLDER = Files.EXTERNAL_MAPS_LOCATION;
    private AssetManager assetManager;
    private FileHandle[] internalMaps;
    private FileHandle[] externalMaps;
    private FileHandle[] allMaps;

    public final FileHandle font16;
    public final FileHandle font32;
    public final FileHandle font64;

    public ResourcesHandler() {
        internalMaps = new FileHandle[Files.MAPS_COUNT];
        for (int i = 0; i < internalMaps.length; i++) {
            internalMaps[i] = Gdx.files.internal(Files.LOCAL_MAPS_LOCATION + Files.MAPS_NAMES[i]);
        }
        if (Gdx.files.isExternalStorageAvailable()) {
            if (Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).isDirectory()) {
                externalMaps = Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).list(Files.MAP_SUFFIX);
            } else {
                Gdx.files.external(DEFAULT_EXTERNAL_MAPS_FOLDER).mkdirs();
                externalMaps = new FileHandle[0];
            }
        }
        assetManager = new AssetManager();
        font16 = Gdx.files.local(Files.FONTS_LOCATION + "font16.fnt");
        font32 = Gdx.files.local(Files.FONTS_LOCATION + "font32.fnt");
        font64 = Gdx.files.local(Files.FONTS_LOCATION + "font64.fnt");
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

    public FileHandle[] getInternalMaps() {
        return internalMaps;
    }

    public FileHandle[] getExternalMaps() {
        return externalMaps;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
