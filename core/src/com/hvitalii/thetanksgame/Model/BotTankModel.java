package com.hvitalii.thetanksgame.Model;

import com.hvitalii.thetanksgame.Constants.BotAttributes;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class BotTankModel extends Tank {

    private int botType;
    private int armourAmount;
    private boolean isBonusCarrier;

    public BotTankModel() {
        super(Direction.DOWN, Speed.NORMAL);
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
        return isBonusCarrier;
    }

    public void setBonusCarrier(boolean bonusCarrier) {
        isBonusCarrier = bonusCarrier;
    }
}
