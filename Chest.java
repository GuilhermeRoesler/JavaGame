public class Chest extends Entity {
    GamePanel gp;

    public Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        down1 = setup(Constants.OBJECTS_PATH, "chest.png", gp.tileSize, gp.tileSize);
    }
}
