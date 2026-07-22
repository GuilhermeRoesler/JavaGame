public class ShieldWood extends Entity {
    GamePanel gp;

    public ShieldWood(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Wood Shield";
        down1 = setup(Constants.OBJECTS_PATH, "shield_wood.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
