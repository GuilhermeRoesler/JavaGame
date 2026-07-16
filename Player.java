import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = gp.screenWidth / 10 * 8;
        y = gp.screenHeight / 2 - gp.tileSize / 2;
        speed = 4;
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
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
