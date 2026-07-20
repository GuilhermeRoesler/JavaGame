import java.awt.Rectangle;

public class Constants {
    public static final String RES_PATH = "./res";
    public static final String PLAYER_PATH = Utils.joinPath(RES_PATH, "player");
    public static final String TILES_PATH = Utils.joinPath(RES_PATH, "tiles");
    public static final String MAPS_PATH = Utils.joinPath(RES_PATH, "maps");
    public static final String OBJECTS_PATH = Utils.joinPath(RES_PATH, "objects");

    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int TILE_SCALE = 3;
    public static final int MAX_SCREEN_COL = 16;
    public static final int MAX_SCREEN_ROW = 12;
    public static final int MAX_WORLD_COL = 50;
    public static final int MAX_WORLD_ROW = 50;
    public static final int FPS = 60;

    public static final boolean IS_COLLISION_VISIBLE = false;
    public static final Rectangle PLAYER_COLLISION_AREA = new Rectangle(8, 30, 32, 20);
}
