public class Door extends Entity {
    GamePanel gp;

    public Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup(Constants.OBJECTS_PATH, "door.png");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
