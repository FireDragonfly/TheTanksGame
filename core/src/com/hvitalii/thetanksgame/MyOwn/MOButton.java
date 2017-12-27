package com.hvitalii.thetanksgame.MyOwn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hvitalii.thetanksgame.Utils.MathUtils;

public class MOButton extends MOLabel {

    private Color color;
    private Color hoverColor;

    public MOButton(FileHandle font, String text, float x, float y) {
        super(font, text, x, y);
    }

    public MOButton(FileHandle font, String text) {
        super(font, text);
    }

    public MOButton(String text) {
        super(text);
    }

    public MOButton() {
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
        Vector2 cursorPosition = MathUtils.getCursorPositionForFitViewport();
        float x = getX();
        switch (getAlignment()) {
            case Align.center:
                x = x - getWidth() / 2;
                break;
            case Align.right:
                x = x - getWidth();
                break;
        }
        if ((cursorPosition.x >= x) && (cursorPosition.x <= (x + getWidth()))
            && (cursorPosition.y >= getY()) && (cursorPosition.y <= (getY() + getHeight()))) {
            return true;
        }
        return false;
    }

    public boolean isPressed() {
        return isHover() && (Gdx.input.isButtonPressed(Input.Buttons.LEFT));
    }

    public boolean isTouched() {
        return isHover() && (Gdx.input.isTouched());
    }

    public boolean justTouched() {
        return isHover() && (Gdx.input.justTouched());
    }
}
