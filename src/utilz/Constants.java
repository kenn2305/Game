package utilz;

public class Constants {
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int ATTACK_1 = 4;
        public static final int ATTACK_2 = 5;
        public static final int AIR_ATTACK = 6;
    }

    public static class PlayerAniConstants {
        public static final int IDLE = 4;
        public static final int RUNNING = 6;
        public static final int JUMPING = 3;
        public static final int FALLING = 3;
        public static final int ATTACK_1 = 5;
        public static final int ATTACK_2 = 6;
        public static final int AIR_ATTACK = 6;
    }

    public static class BlueGolemConstants {
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int HURT = 2;
        public static final int ATTACK = 3;
        public static final int BORN = 4;
        public static final int DIE = 5;
    }

    public static class BlueGolemAniConstants {
        public static final int IDLE = 8;
        public static final int WALK = 8;
        public static final int HURT = 4;
        public static final int ATTACK = 11;
        public static final int BORN = 13;
        public static final int DIE = 13;
    }

    public static class ItemConstants {
        public static final int BLUE_POTION = 0;
        public static final int RED_POTION = 1;
        public static final int HAMBURGER = 2;
    }
    public static class ItemAniConstants {
        public static final int BLUE_POTION = 7;
        public static final int RED_POTION = 7;
        public static final int HAMBURGER = 7;
    }
}
