import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage image, car1, car2, car3;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getPlayerImage();
        setDefaultValues();
    }

    public void getPlayerImage() {
        try {
            car1 = ImageIO.read(new File(Constants.IMG_PATH + "carrinho_sprites_1.png"));
            car2 = ImageIO.read(new File(Constants.IMG_PATH + "carrinho_sprites_2.png"));
            car3 = ImageIO.read(new File(Constants.IMG_PATH + "carrinho_sprites_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        width = gp.tileSize;
        height = gp.tileSize;
        x = gp.screenWidth / 10 * 8;
        y = gp.screenHeight / 2 - height / 2;
        speed = 4;
        image = car1;
    }

    public void update() {
        handleMove();
        updateSprite();
    }

    public void draw(Graphics2D g2) {
        // g2.fillRect(x, y, width, height);
        g2.drawImage(image, x, y, width, height, null);
    }

    public void handleMove() {
        if (keyH.upPressed) {
            if (y <= 0) {
                y = 0;
                return;
            }
            y -= speed;
        }
        else if (keyH.downPressed) {
            if (y >= gp.screenHeight - height) {
                y = gp.screenHeight - height;
                return;
            }
            y += speed;
        }
    }

    public void updateSprite() {
        if (gp.frameNum % 10 == 0) {
            if (image == car1) {
                image = car2;
            } else if (image == car2) {
                image = car3;
            } else if (image == car3) {
                image = car1;
            }
        }
    }
}
