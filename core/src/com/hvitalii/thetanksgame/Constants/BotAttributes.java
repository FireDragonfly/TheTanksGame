package com.hvitalii.thetanksgame.Constants;

import com.hvitalii.thetanksgame.Model.BotTankModel;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;

public class BotAttributes {

    public static final Attributes[] botTypes = new Attributes[] {
            new SmallBot(),
            new APCBot(),
            new FastBot(),
            new HeavyBot()
    };

    public static abstract class Attributes {
        public abstract void loadAttributes(BotTankModel tank);
    }

    public static class SmallBot extends Attributes{
        public static final int ARMOUR_EMOUNT = 0;
        public static final float SPEED = Speed.NORMAL;
        public static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_EMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class APCBot extends Attributes{
        public static final int ARMOUR_EMOUNT = 0;
        public static final float SPEED = Speed.FAST;
        public static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_EMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class FastBot extends Attributes{
        public static final int ARMOUR_EMOUNT = 0;
        public static final float SPEED = Speed.NORMAL;
        public static final int BULLET_TYPE = BulletsTypes.FAST;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_EMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class HeavyBot extends Attributes{
        public static final int ARMOUR_EMOUNT = 2;
        public static final float SPEED = Speed.NORMAL;
        public static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_EMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }
}
