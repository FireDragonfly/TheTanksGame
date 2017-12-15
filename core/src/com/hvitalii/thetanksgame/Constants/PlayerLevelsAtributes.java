package com.hvitalii.thetanksgame.Constants;

import com.hvitalii.thetanksgame.Model.PlayerTankModel;
import com.hvitalii.thetanksgame.Constants.ObjectsConstants .*;

public class PlayerLevelsAtributes {
    public static final Attributes[] levels = new Attributes[] {
            new FirstLevel(),
            new SecondLevel(),
            new ThirdLevel(),
            new FourthLevel()
    };

    public static abstract class Attributes {
        public abstract void loadAttributes(PlayerTankModel tank);
    }

    public static class FirstLevel extends Attributes{
        public static final int BULLET_TYPE = BulletsTypes.NORMAL;
        public static final int BULLET_MAX = 1;

        @Override
        public void loadAttributes(PlayerTankModel tank) {
            tank.setBulletsType(BULLET_TYPE);
            tank.setBulletsMax(BULLET_MAX);
        }
    }

    public static class SecondLevel extends Attributes{
        public static final int BULLET_TYPE = BulletsTypes.FAST;
        public static final int BULLET_MAX = 1;

        @Override
        public void loadAttributes(PlayerTankModel tank) {
            tank.setBulletsType(BULLET_TYPE);
            tank.setBulletsMax(BULLET_MAX);
        }
    }



    public static class ThirdLevel extends Attributes{
        public static final int BULLET_TYPE = BulletsTypes.FAST;
        public static final int BULLET_MAX = 2;

        @Override
        public void loadAttributes(PlayerTankModel tank) {
            tank.setBulletsType(BULLET_TYPE);
            tank.setBulletsMax(BULLET_MAX);
        }
    }

    public static class FourthLevel extends Attributes{
        public static final int BULLET_TYPE = BulletsTypes.AP;
        public static final int BULLET_MAX = 2;

        @Override
        public void loadAttributes(PlayerTankModel tank) {
            tank.setBulletsType(BULLET_TYPE);
            tank.setBulletsMax(BULLET_MAX);
        }
    }
}
