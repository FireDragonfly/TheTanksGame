package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.BotAttributes;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class BotTankModel extends TankModel {

    private int botType;
    private int armourAmount;
    private int bonus;

    public BotTankModel(Rectangle rectangle, int bonus) {
        super(rectangle, Direction.DOWN, Speed.NORMAL);
        this.bonus = bonus;
    }

    public int getBotType() {
        return botType;
    }

    public void setBotType(int botType) {
        if ((botType >= BotTypes.SMALL) && (botType <= BotTypes.HEAVY)) {
            this.botType = botType;
        } else {
            this.botType = BotTypes.SMALL;
        }
        BotAttributes.botTypes[botType].loadAttributes(this);
    }

    public int getArmourAmount() {
        return armourAmount;
    }

    public void setArmourAmount(int armourAmount) {
        this.armourAmount = armourAmount;
    }

    public boolean isBonusCarrier() {
        return bonus >= 0;
    }

    public void setBonusCarrier(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus(){
        return bonus;
    }
}
