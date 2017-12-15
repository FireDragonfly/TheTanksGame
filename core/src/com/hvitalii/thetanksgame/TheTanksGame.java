package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hvitalii.thetanksgame.View.GameScreen;
//import com.hvitalii.thetanksgame.View.GameTextures;
//import com.hvitalii.thetanksgame.View.LoadingScreen;

public class TheTanksGame extends Game {

	@Override
	public void create () {
	    AssetsHandler assetsHandler = new AssetsHandler();
	    assetsHandler.loadAssets();
        while (assetsHandler.getAssetManager().getProgress() < 1){
            System.out.println(assetsHandler.getAssetManager().getProgress());
        }
		GameScreen gameScreen = new GameScreen(assetsHandler);
	    setScreen(gameScreen);
	}

    @Override
    public void dispose() {
        super.dispose();

    }
}
