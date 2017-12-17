package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
//import com.hvitalii.thetanksgame.View.GameTextures;
//import com.hvitalii.thetanksgame.View.LoadingScreen;

public class TheTanksGame extends Game {

	@Override
	public void create () {
	    ResourcesHandler resourcesHandler = new ResourcesHandler();
	    resourcesHandler.loadAssets();
		GameScreen gameScreen = new GameScreen(resourcesHandler);
	    setScreen(gameScreen);
	}

    @Override
    public void dispose() {
        super.dispose();

    }
}
