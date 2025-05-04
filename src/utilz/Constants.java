package utilz;

import Game.Game;

public class Constants {
    public static class MenuButton{
        public static final int MENU_BUTTON_DEFAULT_WIDTH = 69;
        public static final int MENU_BUTTON_DEFAULT_HEIGHT = 21;
        public static final int MENU_BUTTON_WIDTH = (int) (MENU_BUTTON_DEFAULT_WIDTH * Game.GAME_SCALE * 3);
        public static final int MENU_BUTTON_HEIGHT  = (int) (MENU_BUTTON_DEFAULT_HEIGHT * Game.GAME_SCALE * 3);
        public static final int BACKGROUND_BUTTON_DEFAULT_WIDTH = 100;
        public static final int BACKGROUND_BUTTON_DEFAULT_HEIGHT = 117;
        public static final int BACKGROUND_BUTTON_WIDTH = (int) (BACKGROUND_BUTTON_DEFAULT_WIDTH * Game.GAME_SCALE * 2);
        public static final int BACKGROUND_BUTTON_HEIGHT = (int) (BACKGROUND_BUTTON_DEFAULT_HEIGHT * Game.GAME_SCALE * 2);
    }
    public static class PauseButton{
        public static final int SOUND_SIZE_DEFAULT_WIDTH = 23;
        public static final int SOUND_SIZE_DEFAULT_HEIGHT = 21;
        public static final int SOUND_SIZE_WIDTH = (int) (SOUND_SIZE_DEFAULT_WIDTH * Game.GAME_SCALE * 2.5);
        public static final int SOUND_SIZE_HEIGHT = (int) (SOUND_SIZE_DEFAULT_HEIGHT * Game.GAME_SCALE * 2.5);
    }
    public static class URMButton{
        public static final int URM_SIZE_DEFAULT_WIDTH = 23;
        public static final int URM_SIZE_DEFAULT_HEIGHT = 21;
        public static final int URM_SIZE_WIDTH = (int) (URM_SIZE_DEFAULT_WIDTH * Game.GAME_SCALE * 2.5);
        public static final int URM_SIZE_HEIGHT = (int) (URM_SIZE_DEFAULT_HEIGHT * Game.GAME_SCALE * 2.5);
    }
    public static class VolumeButton{
        public static final int VOLUME_SIZE_DEFAULT_WIDTH = 10;
        public static final int VOLUME_SIZE_DEFAULT_HEIGHT = 21;
        public static final int VOLUME_SIZE_WIDTH = (int) (VOLUME_SIZE_DEFAULT_WIDTH * Game.GAME_SCALE * 2.5);
        public static final int VOLUME_SIZE_HEIGHT = (int) (VOLUME_SIZE_DEFAULT_HEIGHT * Game.GAME_SCALE * 2.5);
        public static final int SLIDER_DEFAULT_WIDTH = 69;
        public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.GAME_SCALE * 2.5);
    }
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
