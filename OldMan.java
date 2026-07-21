import java.util.Random;

public class OldMan extends Entity {
    public OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
    }

    public void getNPCImage() {
        up1 = setup(Constants.NPC_PATH, "oldman_up_1.png");
        up2 = setup(Constants.NPC_PATH, "oldman_up_2.png");
        down1 = setup(Constants.NPC_PATH, "oldman_down_1.png");
        down2 = setup(Constants.NPC_PATH, "oldman_down_2.png");
        left1 = setup(Constants.NPC_PATH, "oldman_left_1.png");
        left2 = setup(Constants.NPC_PATH, "oldman_left_2.png");
        right1 = setup(Constants.NPC_PATH, "oldman_right_1.png");
        right2 = setup(Constants.NPC_PATH, "oldman_right_2.png");
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter > 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
