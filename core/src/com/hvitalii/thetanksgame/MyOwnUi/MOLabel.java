package com.hvitalii.thetanksgame.MyOwnUi;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class MOLabel implements Disposable{

    private BitmapFont font;
    private Color color;
    private String[] text;

    private float x;
    private float y;
    private float scaleX;
    private float scaleY;
    private float height;
    private float width;
    private int textAlignment;
    private int alignment;



    public MOLabel(FileHandle font, String text, float x, float y) {
        this(font, text);
        this.x = x;
        this.y = y;
    }

    public MOLabel(FileHandle font, String text) {
        this();
        this.font = new BitmapFont(font);
        setText(text);
    }

    public MOLabel(String text) {
        this();
        setText(text);
    }

    public MOLabel(FileHandle font) {
        this();
        this.font = new BitmapFont(font);
    }

    public MOLabel() {
        font = new BitmapFont();
        scaleX = font.getScaleX();
        scaleY = font.getScaleY();
        x = 0;
        y = 0;
        textAlignment = Align.left;
        setText("Label");
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        font.setColor(color);
    }

    public void draw(SpriteBatch batch) {
        float alignedX = x;
        switch (alignment) {
            case Align.center:
                alignedX = x - width / 2;
                break;
            case Align.right:
                alignedX = x - width;
                break;
        }
        for (int i = 0; i < text.length; i++) {
            float stringY = y + (height - getGlyphHeight() * i);
            switch (textAlignment) {
                case Align.left:
                    font.draw(batch, text[i], alignedX, stringY);
                break;
                case Align.center:
                    font.draw(batch, text[i], alignedX + (width - getStringWidth(text[i])) / 2, stringY);
                break;
                case Align.right:
                    font.draw(batch, text[i], alignedX + (width - getStringWidth(text[i])), stringY);
                break;
            }
        }
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(float x, float y, int alignment) {
        this.x = x;
        this.y = y;
        setAlignment(alignment);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getText() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < text.length ;i++) {
            string.append(text[i]);
            if (i != text.length - 1) {
                string.append("\n");
            }
        }
        return string.toString();
    }

    public void setText(String text) {
        this.text = text.split("\n");
        update();
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
        font.getData().setScale(scaleX, scaleY);
        update();
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
        font.getData().setScale(scaleX, scaleY);
        update();
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
        font.getData().setScale(scaleX, scaleY);
        update();
    }

    public int getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(int alignment) {
        if (alignment <= Align.right && alignment >= Align.left) {
            this.textAlignment = alignment;
        } else {
            this.textAlignment = Align.left;
        }
    }

    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(int alignment) {
        if (alignment <= Align.right && alignment >= Align.left) {
            this.alignment = alignment;
        } else {
            this.alignment = Align.left;
        }
    }

    public BitmapFont getFont() {
        return font;
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public static class Align{
        public static final int left = 0;
        public static final int center = 1;
        public static final int right = 2;
    }

    private void update() {
        updateWidth();
        updateHeight();
    }

    private void updateWidth() {
        float maxWidth = 0;
        float width = 0;
        for (String s : text) {
            width = getStringWidth(s);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        this.width = maxWidth;
    }

    private void updateHeight() {
        this.height = getGlyphHeight() * text.length;
    }

    private float getStringWidth(String s) {
        char[] letters = s.toCharArray();
        int width = 0;
        for (char c : letters) {
            if (font.getData().getGlyph(c) != null) {
                width += font.getData().getGlyph(c).width;
            }
        }
        return width * scaleX;
    }

    private float getGlyphHeight() {
        return font.getData().getFirstGlyph().height * scaleY;
    }
}
