package com.hvitalii.thetanksgame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hvitalii.thetanksgame.View.GameScreen;

public class TheTanksGame extends Game {

	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create () {
		Screen gameScreen = new GameScreen();
	    setScreen(gameScreen);
	}
}
