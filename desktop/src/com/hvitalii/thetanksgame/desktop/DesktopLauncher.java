package com.hvitalii.thetanksgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hvitalii.thetanksgame.TheTanksGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Tanks Game";
		config.width = 770;
		config.height = 670;
		new LwjglApplication(new TheTanksGame(), config);
	}
}
