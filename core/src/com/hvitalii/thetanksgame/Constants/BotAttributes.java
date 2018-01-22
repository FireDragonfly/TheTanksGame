package com.hvitalii.thetanksgame.Constants;
import com.hvitalii.thetanksgame.Model.BotTankModel;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
public class BotAttributes {

    public static final Attributes[] BOT_TYPES = new Attributes[] {
            new SmallBot(),
            new APCBot(),
            new FastBot(),
            new HeavyBot()
    };

    public static abstract class Attributes {
        public abstract void loadAttributes(BotTankModel tank);
    }

    public static class SmallBot extends Attributes{
        static final int ARMOUR_AMOUNT = 0;
        static final float SPEED = Speed.SLOW;
        static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_AMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class APCBot extends Attributes{
        static final int ARMOUR_AMOUNT = 0;
        static final float SPEED = Speed.FAST;
        static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_AMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class FastBot extends Attributes{
        static final int ARMOUR_AMOUNT = 0;
        static final float SPEED = Speed.NORMAL;
        static final int BULLET_TYPE = BulletsTypes.FAST;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_AMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }

    public static class HeavyBot extends Attributes{
        static final int ARMOUR_AMOUNT = 3;
        static final float SPEED = Speed.SLOW;
        static final int BULLET_TYPE = BulletsTypes.NORMAL;

        @Override
        public void loadAttributes(BotTankModel tank) {
            tank.setArmourAmount(ARMOUR_AMOUNT);
            tank.setSpeed(SPEED);
            tank.setBulletsType(BULLET_TYPE);
        }
    }
}
