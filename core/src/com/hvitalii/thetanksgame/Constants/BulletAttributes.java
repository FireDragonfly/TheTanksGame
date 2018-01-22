package com.hvitalii.thetanksgame.Constants;

import com.hvitalii.thetanksgame.Constants.ObjectsConstants.*;
import com.hvitalii.thetanksgame.Model.BulletModel;

public class BulletAttributes {

        public static final Attributes[] BULLET_TYPES = new Attributes[] {
                new Bullet(),
                new FastBullet(),
                new APBullet()
        };

        public static abstract class Attributes {
            public abstract void loadAttributes(BulletModel bullet);
        }

        public static class Bullet extends Attributes {
            public static final float SPEED = Speed.NORMAL_BULLET_SPEED;

            @Override
            public void loadAttributes(BulletModel bullet) {
                bullet.setSpeed(SPEED);
            }
        }

        public static class FastBullet extends Attributes {
            public static final float SPEED = Speed.FAST_BULLET_SPEED;

            @Override
            public void loadAttributes(BulletModel bullet) {
                bullet.setSpeed(SPEED);
            }
        }

        public static class APBullet extends Attributes {
            public static final float SPEED = Speed.FAST_BULLET_SPEED;

            @Override
            public void loadAttributes(BulletModel bullet) {
                bullet.setSpeed(SPEED);
            }
        }

}
