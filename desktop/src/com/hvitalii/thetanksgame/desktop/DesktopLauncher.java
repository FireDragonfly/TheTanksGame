package com.hvitalii.thetanksgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;
import com.hvitalii.thetanksgame.TheTanksGame;

public class DesktopLauncher {

	private static final int SCREEN_FACTOR = 3;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Tanks Game";
		config.width = (int)Resolution.SCREEN_WIDTH * SCREEN_FACTOR;
		config.height = (int)Resolution.SCREEN_HEIGHT * SCREEN_FACTOR;
		boolean testMod = (arg.length > 0)&&(arg[0].equals("test"));
		if (testMod) System.out.println("test mod activated");
		new LwjglApplication(new TheTanksGame(testMod), config);
	}
}
