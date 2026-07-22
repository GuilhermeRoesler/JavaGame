import java.awt.Rectangle;
import java.util.Random;

public class OldMan extends Entity {
    public OldMan(GamePanel gp) {
        super(gp);

        solidArea = new Rectangle(8, 30, 32, 20);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        direction = "down";
        speed = 1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup(Constants.NPC_PATH, "oldman_up_1.png", gp.tileSize, gp.tileSize);
        up2 = setup(Constants.NPC_PATH, "oldman_up_2.png", gp.tileSize, gp.tileSize);
        down1 = setup(Constants.NPC_PATH, "oldman_down_1.png", gp.tileSize, gp.tileSize);
        down2 = setup(Constants.NPC_PATH, "oldman_down_2.png", gp.tileSize, gp.tileSize);
        left1 = setup(Constants.NPC_PATH, "oldman_left_1.png", gp.tileSize, gp.tileSize);
        left2 = setup(Constants.NPC_PATH, "oldman_left_2.png", gp.tileSize, gp.tileSize);
        right1 = setup(Constants.NPC_PATH, "oldman_right_1.png", gp.tileSize, gp.tileSize);
        right2 = setup(Constants.NPC_PATH, "oldman_right_2.png", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
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

    public void speak() {
        super.speak();
    }
}
