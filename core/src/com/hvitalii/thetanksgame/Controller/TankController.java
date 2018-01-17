package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Model.TankModel;

public interface TankController extends Controller{
    void bulletDestroyed();
    Rectangle getBounds();
    TankModel getModel();
    boolean hitOn(BulletController bullet);
    int getType();
//    public boolean isDestructed();
}
