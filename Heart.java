public class Heart extends Entity {
    GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup(Constants.OBJECTS_PATH, "heart_full.png", gp.tileSize, gp.tileSize);
        image2 = setup(Constants.OBJECTS_PATH, "heart_half.png", gp.tileSize, gp.tileSize);
        image3 = setup(Constants.OBJECTS_PATH, "heart_blank.png", gp.tileSize, gp.tileSize);
    }
}
