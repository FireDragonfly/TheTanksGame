package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hvitalii.thetanksgame.Constants.GameConstants.*;

public class MyOwnButton extends MyOwnLabel {

    private Color color;
    private Color hoverColor;

    public MyOwnButton(FileHandle font, String text, float x, float y) {
        super(font, text, x, y);
    }

    public MyOwnButton(FileHandle font, String text) {
        super(font, text);
    }

    public MyOwnButton(String text) {
        super(text);
    }

    public MyOwnButton() {
        super();
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(color != null && hoverColor != null)
        if (isHover()) {
            super.setColor(hoverColor);
        } else {
            super.setColor(color);
        }
        super.draw(batch);

    }

    @Override
    public void setColor(Color color) {
        this.color = color;
        super.setColor(color);
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public boolean isHover() {
        float synchronizedX = Gdx.graphics.getWidth() / Resolution.SCREEN_WIDTH;
        float synchronizedY = Gdx.graphics.getHeight() / Resolution.SCREEN_HEIGHT;
        float cursorX = Gdx.input.getX() / synchronizedX;
        float cursorY = (-Gdx.input.getY() + Gdx.graphics.getHeight()) / synchronizedY;
        float x = getX();
        switch (getAlignment()) {
            case Align.center:
                x = x - getWidth() / 2;
                break;
            case Align.right:
                x = x - getWidth();
                break;
        }
        if ((cursorX >= x) && (cursorX <= (x + getWidth()))
            && (cursorY >= getY()) && (cursorY <= (getY() + getHeight()))) {
            return true;
        }
        return false;
    }

    public boolean isPressed() {
        return isHover() && (Gdx.input.isButtonPressed(Input.Buttons.LEFT));
    }
}
