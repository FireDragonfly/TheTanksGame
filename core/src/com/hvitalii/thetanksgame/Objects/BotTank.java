package com.hvitalii.thetanksgame.Objects;

public class BotTank extends Tank {

    private int botType;
    private int armourAmount;
    private boolean isBonusCarrier;

    public BotTank() {
        super(Types.BOT, Direction.DOWN, Speed.NORMAL);
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
        if (armourAmount >= 0) {
            this.armourAmount = armourAmount;
        } else {
            this.armourAmount = 0;
        }
    }

    public boolean isBonusCarrier() {
        return isBonusCarrier;
    }

    public void setBonusCarrier(boolean bonusCarrier) {
        isBonusCarrier = bonusCarrier;
    }

    @Override
    public int[] getVisualCondition() {
        int[] condition = new int[6];
        condition[0] = getType();
        condition[1] = botType;
        condition[2] = isBonusCarrier ? 1 : 0;
        condition[3] = armourAmount;
        condition[4] = getDirection();
        condition[5] = getAnimationFrame();
        return condition;
    }

    @Override
    public void move(int direction) {
        switchAnimationFrame();
    }
}
