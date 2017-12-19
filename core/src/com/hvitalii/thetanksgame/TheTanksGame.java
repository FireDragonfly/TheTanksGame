package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
//import com.hvitalii.thetanksgame.View.GameTextures;
//import com.hvitalii.thetanksgame.View.LoadingScreen;

public class TheTanksGame extends Game {

	private ResourcesHandler resourcesHandler;

	@Override
	public void create () {
	    resourcesHandler = new ResourcesHandler();
	    resourcesHandler.loadAssets();
	    GameController state = new GameController(resourcesHandler, 1);
		GameScreen gameScreen = new GameScreen(state);
	    setScreen(gameScreen);
	}

	public ResourcesHandler getResourcesHandler() {
		return resourcesHandler;
	}

	@Override
    public void dispose() {
        super.dispose();
		resourcesHandler.dispose();
    }
}
