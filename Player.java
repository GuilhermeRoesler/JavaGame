import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
        image = car1;
    }

    public void setDefaultValues() {
        x = gp.screenWidth / 10 * 8;
        y = gp.screenHeight / 2 - gp.tileSize / 2;
        speed = 4;
    }

    public void getPlayerImage() {
        try {
            car1 = ImageIO.read(new File("./img/carrinho_sprites_1.png"));
            car2 = ImageIO.read(new File("./img/carrinho_sprites_2.png"));
            car3 = ImageIO.read(new File("./img/carrinho_sprites_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            if (y <= 0) {
                y = 0;
                return;
            }
            y -= speed;
        }
        else if (keyH.downPressed) {
            if (y >= gp.screenHeight - gp.tileSize) {
                y = gp.screenHeight - gp.tileSize;
                return;
            }
            y += speed;
        }

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

    public void draw(Graphics2D g2) {
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
