package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.hvitalii.thetanksgame.Screens.GameScreen;
import com.hvitalii.thetanksgame.Screens.MainMenuScreen;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;
//import com.hvitalii.thetanksgame.View.GameTextures;
//import com.hvitalii.thetanksgame.View.LoadingScreen;

public class TheTanksGame extends Game {

	private ResourcesHandler resourcesHandler;
	private MainMenuScreen mainMenuScreen;

	@Override
	public void create () {
	    resourcesHandler = new ResourcesHandler();
	    resourcesHandler.loadAssets();
	    Statistic statistic = new Statistic();
	    mainMenuScreen = new MainMenuScreen(resourcesHandler, this, statistic);
	    setScreen(mainMenuScreen);
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
