package com.hvitalii.thetanksgame.Model;

import com.badlogic.gdx.math.Rectangle;
import com.hvitalii.thetanksgame.Constants.BotAttributes;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class BotTankModel extends TankModel {

    private int botType;
    private int armourAmount;
    private boolean isBonusCarrier;

    public BotTankModel(Rectangle rectangle, boolean isBonusCarrier) {
        super(rectangle, Direction.DOWN, Speed.NORMAL);
        this.isBonusCarrier = isBonusCarrier;
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
        BotAttributes.BOT_TYPES[botType].loadAttributes(this);
    }

    public int getArmourAmount() {
        return armourAmount;
    }

    public void setArmourAmount(int armourAmount) {
        this.armourAmount = armourAmount;
    }

    public boolean isBonusCarrier() {
        return isBonusCarrier;
    }

    public void setBonusCarrier(boolean isBonusCarrier) {
        this.isBonusCarrier =  isBonusCarrier;
    }
}
