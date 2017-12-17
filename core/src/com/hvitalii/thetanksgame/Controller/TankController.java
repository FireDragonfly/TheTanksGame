package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Model.TankModel;

public interface TankController extends Controller{
    public void bulletDestroyed();
    public Rectangle getBounds();
    public TankModel getModel();
    public void hitOn(BulletController bullet);
//    public boolean isDestructed();
}
