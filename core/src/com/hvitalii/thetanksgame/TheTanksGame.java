package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.hvitalii.thetanksgame.View.GameScreen;

public class TheTanksGame extends Game {

    private Screen gameScreen;

	@Override
	public void create () {
	    gameScreen = new GameScreen();
	    setScreen(gameScreen);
	}
}
