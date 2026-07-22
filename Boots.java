public class Boots extends Entity {
    GamePanel gp;

    public Boots(GamePanel gp) {
        super(gp);
        name = "Boots";
        down1 = setup(Constants.OBJECTS_PATH, "boots.png", gp.tileSize, gp.tileSize);
    }
}
