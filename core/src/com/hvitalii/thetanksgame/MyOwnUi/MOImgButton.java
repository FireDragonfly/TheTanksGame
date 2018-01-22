package com.hvitalii.thetanksgame.MyOwnUi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.hvitalii.thetanksgame.Utils.MathUtils;

import static com.hvitalii.thetanksgame.MyOwnUi.MOLabel.*;

public class MOImgButton extends MOAdvancedSprite {

    private Color color;
    private Color hoverColor;

    public MOImgButton(Texture texture) {
        super(texture);
        color = super.getColor();
    }

    public MOImgButton(TextureRegion region) {
        super(region);
        color = super.getColor();
    }

    public void draw(SpriteBatch batch) {
        if (hoverColor != null) {
            if (isHover()) {
                super.setColor(hoverColor);
            } else {
                super.setColor(color);
            }
        }
        super.draw(batch);
    }

    public void draw(SpriteBatch batch, Color color) {
        super.setColor(color);
        super.draw(batch);
    }

    public void setPosition(float x, float y, int alignment) {
        switch (alignment) {
            case Align.center:
                x = x - getWidth() / 2;
                break;
            case Align.right:
                x = x - getWidth();
                break;
        }
        super.setPosition(x, y);
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
        return (cursorPosition.x >= x) && (cursorPosition.x <= (x + getWidth()))
                && (cursorPosition.y >= getY()) && (cursorPosition.y <= (getY() + getHeight()));
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
