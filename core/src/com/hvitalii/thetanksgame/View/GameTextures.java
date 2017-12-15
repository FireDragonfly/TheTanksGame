//package com.hvitalii.thetanksgame.View;
//
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.Disposable;
//
//public class GameTextures implements Disposable{
//
//    private TextureRegion[][][][][][] objectsTextureRegions;
//
//    public GameTextures() {
//
//    }
//
//    public void loadTextures(AssetManager assetManager) {
//
//        TextureAtlas atlas;
//        atlas = assetManager.get("TextureAtlases/tiles.atlas");
//        TextureRegion[] eagle = new TextureRegion[2];
//        eagle[0] = (TextureRegion) regions.get(0);
//        eagle[1] = (TextureRegion) regions.get(1);
//
//
//        atlas = assetManager.get("TextureAtlases/bullets.atlas");
//        regions = atlas.getRegions();
//
//        atlas = assetManager.get("TextureAtlases/Tanks/small.atlas");
//        regions = atlas.getRegions();
//        atlas = assetManager.get("TextureAtlases/Tanks/apc.atlas");
//        regions = atlas.getRegions();
//        atlas = assetManager.get("TextureAtlases/Tanks/medium.atlas");
//        regions = atlas.getRegions();
//        atlas = assetManager.get("TextureAtlases/Tanks/heavy.atlas");
//        regions = atlas.getRegions();
//        atlas = assetManager.get("TextureAtlases/Tanks/p1.atlas");
//        regions = atlas.getRegions();
//        atlas = assetManager.get("TextureAtlases/Tanks/p2.atlas");
//        regions = atlas.getRegions();
//
//
//        atlas = assetManager.get("TextureAtlases/animation.atlas");
//        regions = atlas.getRegions();
//    }
//
//    @Override
//    public void dispose() {
//
//    }
//}
