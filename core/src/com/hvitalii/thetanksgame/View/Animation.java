package com.hvitalii.thetanksgame.View;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation extends com.badlogic.gdx.graphics.g2d.Animation {
    public Animation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
    }
}
