public class Key extends Entity {
    GamePanel gp;

    public Key(GamePanel gp) {
        super(gp);
        name = "Key";
        down1 = setup(Constants.OBJECTS_PATH, "key.png");
    }
}
