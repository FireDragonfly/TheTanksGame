package com.hvitalii.thetanksgame.Controller;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.View.PlayerTankView;

public class PlayerTank {

    private PlayerTankModel model;
    private PlayerTankView view;

    public PlayerTank(TextureAtlas atlas, int playerNumber) {
        model = new PlayerTankModel(playerNumber);
        view = new PlayerTankView(atlas, model);
    }

    public void update() {

    }

}
