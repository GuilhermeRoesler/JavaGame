public class SwordNormal extends Entity {
    GamePanel gp;

    public SwordNormal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Normal Sword";
        down1 = setup(Constants.OBJECTS_PATH, "sword_normal.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
