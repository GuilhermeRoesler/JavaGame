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
}
