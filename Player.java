import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int keysNum;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth - gp.tileSize) / 2;
        screenY = (gp.screenHeight - gp.tileSize) / 2;

        solidArea = Constants.PLAYER_COLLISION_AREA;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_up_1.png")));
            up2 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_up_2.png")));
            down1 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_down_1.png")));
            down2 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_down_2.png")));
            left1 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_left_1.png")));
            left2 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_left_2.png")));
            right1 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_right_1.png")));
            right2 = ImageIO.read(new File(Utils.joinPath(Constants.PLAYER_PATH, "boy_right_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }

            collisionOn = false;
            gp.collisionM.checkTile(this);

            int objInex = gp.collisionM.checkObject(this, true);
            pickUpObject(objInex);

            if (!collisionOn) {
                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "right": worldX += speed; break;
                    case "left": worldX -= speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    keysNum++;
                    gp.obj[index] = null;
                    break;
                case "Door":
                    if (keysNum > 0) {
                        gp.playSE(3);
                        gp.obj[index] = null;
                        keysNum--;
                    }
                    break;
                    case "Boots":
                    gp.playSE(2);
                    speed += 2;
                    gp.obj[index] = null;
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        if (Constants.IS_COLLISION_VISIBLE) {
            g2.setColor(Color.RED);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
