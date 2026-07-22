public class Heart extends Entity {
    GamePanel gp;

    public Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = setup(Constants.OBJECTS_PATH, "heart_full.png");
        image2 = setup(Constants.OBJECTS_PATH, "heart_half.png");
        image3 = setup(Constants.OBJECTS_PATH, "heart_blank.png");
    }
}
