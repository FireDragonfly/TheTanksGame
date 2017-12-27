package com.hvitalii.thetanksgame.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;

public class MathUtils {

    public static float clingToGrid(float n) {
        if ((n % Resolution.TILE_SIZE) > (Resolution.TILE_SIZE / 2)) {
            n += Resolution.TILE_SIZE;
        }
        n -= n % Resolution.TILE_SIZE;
        return n;
    }

    public static float trimToGrid(float n) {
        n -= n % Resolution.TILE_SIZE;
        return n;
    }

    public static Vector2 getCursorPositionForStretchViewport() {
        Vector2 position = new Vector2();
        float synchronizedX = Gdx.graphics.getWidth() / Resolution.SCREEN_WIDTH;
        float synchronizedY = Gdx.graphics.getHeight() / Resolution.SCREEN_HEIGHT;
        position.x = Gdx.input.getX() / synchronizedX;
        position.y = (-Gdx.input.getY() + Gdx.graphics.getHeight()) / synchronizedY;
        return position;
    }

    public static Vector2 getCursorPositionForFitViewport() {
        Vector2 position = new Vector2();
        float gameScreenWidth = Gdx.graphics.getWidth();
        float gameScreenHeight = Gdx.graphics.getHeight();
        {
            float gameAspectRatio = Resolution.SCREEN_HEIGHT / Resolution.SCREEN_WIDTH;
            float screenAspectRatio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
            if (screenAspectRatio != gameAspectRatio) {
                if (screenAspectRatio > gameAspectRatio) {
                    float n = screenAspectRatio / gameAspectRatio;
                    gameScreenHeight /= n;
                } else {
                    float n = gameAspectRatio / screenAspectRatio;
                    gameScreenWidth /= n;
                }
            }
        }
        float scaleWidth = gameScreenWidth / Resolution.SCREEN_WIDTH;
        float scaleHeight = gameScreenHeight / Resolution.SCREEN_HEIGHT;
        position.x = (Gdx.input.getX() / scaleWidth) - ((Gdx.graphics.getWidth() - gameScreenWidth) / 2) / scaleWidth;
        position.y = (((-Gdx.input.getY() + Gdx.graphics.getHeight()) / scaleHeight))  - ((Gdx.graphics.getHeight() - gameScreenHeight) / 2) / scaleHeight;
        return position;
    }

    public static float trim(float n) {
        n -= n - ((int)n);
        return n;
    }
}