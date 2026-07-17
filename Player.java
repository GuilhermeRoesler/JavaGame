import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    BufferedImage image;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getPlayerImage();
        setDefaultValues();
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(new File(Constants.IMG_PATH + "car1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        width = Constants.TILE_SIZE;
        height = Constants.TILE_SIZE;
        x = Constants.SCREEN_WIDTH / 10 * 8;
        y = Constants.SCREEN_HEIGHT / 2 - height / 2;
        speed = 4;
    }

    public void update() {
        handleMove();
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
            if (y >= Constants.SCREEN_HEIGHT - height) {
                y = Constants.SCREEN_HEIGHT - height;
                return;
            }
            y += speed;
        }
    }
}
