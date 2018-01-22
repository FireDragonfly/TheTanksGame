package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.hvitalii.thetanksgame.Screens.MainMenuScreen;
import com.hvitalii.thetanksgame.Utils.ResourcesHandler;

public class TheTanksGame extends Game {

	private ResourcesHandler resourcesHandler;
	private MainMenuScreen mainMenuScreen;
	private boolean testMod;

	public TheTanksGame() {
		testMod = false;
	}

	public TheTanksGame(boolean testMod) {
		this.testMod = testMod;
	}

	@Override
	public void create () {
	    resourcesHandler = new ResourcesHandler();
	    resourcesHandler.loadAssets();
	    Statistic statistic = new Statistic(resourcesHandler);
	    setScreen(new MainMenuScreen(resourcesHandler, this, statistic));
	}

	public ResourcesHandler getResourcesHandler() {
		return resourcesHandler;
	}

	@Override
    public void dispose() {
        super.dispose();
		resourcesHandler.dispose();
    }

	public boolean isTestMod() {
		return testMod;
	}
}
