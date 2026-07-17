public class Constants {
    public static final String IMG_PATH = "./img/";

    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SCALE = 3;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int FPS = 60;
    
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public static final double SPEED_INCREASE_RATE = 120.0;
    public static final int INITIAL_ENEMY_SPAWN_RATE = 60;
    public static final double MAX_SPEED = 20.0;

    public static final boolean IS_IMORTAL = false;
    public static final boolean IS_MULTIPLAYER = false;
}
