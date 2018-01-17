package com.hvitalii.thetanksgame.MyOwnUi;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MOAdvancedSprite extends Sprite{

    private int repeatX;
    private int repeatY;

    {
        repeatX = 1;
        repeatY = 1;
    }

    public MOAdvancedSprite() {
    }

    public MOAdvancedSprite(Texture texture) {
        super(texture);
    }

    public MOAdvancedSprite(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
    }

    public MOAdvancedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public MOAdvancedSprite(TextureRegion region) {
        super(region);
    }

    public MOAdvancedSprite(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public MOAdvancedSprite(Sprite sprite) {
        super(sprite);
    }

    @Override
    public float getWidth() {
        return super.getWidth() * repeatX;
    }

    @Override
    public float getHeight() {
        return super.getHeight() * repeatY;
    }

    public int getRepeatX() {
        return repeatX;
    }

    public void setRepeatX(int repeatX) {
        this.repeatX = (repeatX > 0) ? repeatX : 1;
    }

    public int getRepeatY() {
        return repeatY;
    }

    public void setRepeatY(int repeatY) {
        this.repeatY = (repeatY > 0) ? repeatY : 1;
    }

    public void setRepeat(int XY) {
        setRepeatX(XY);
        setRepeatY(XY);
    }

    @Override
    public void draw(Batch batch) {
        float x = getX();
        float y = getY();
        for (int i = 0; i < repeatY; i++) {
            for (int j = 0; j < repeatX; j++) {
                setPosition(x + (super.getWidth() * j), y + (super.getHeight() * i));
                super.draw(batch);
            }
        }
        setPosition(x, y);
    }
}
