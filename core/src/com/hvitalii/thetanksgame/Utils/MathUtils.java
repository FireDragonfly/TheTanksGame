package com.hvitalii.thetanksgame.Utils;

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

    public static float trim(float n) {
        n -= n - ((int)n);
        return n;
    }
}