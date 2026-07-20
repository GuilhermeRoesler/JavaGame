import java.awt.Rectangle;

public class Constants {
    public static final String RES_PATH = "./res";
    public static final String MAPS_PATH = Utils.joinPath(RES_PATH, "maps");

    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SCALE = 3;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int FPS = 60;
    
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * TILE_SCALE;
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    // WORLD SETTINGS
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public static final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    public static final boolean IS_COLLISION_VISIBLE = false;
    public static final Rectangle PLAYER_COLLISION_AREA = new Rectangle(8, 30, 32, 20);
}
