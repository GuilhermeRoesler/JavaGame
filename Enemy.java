import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {
    GamePanel gp;
    Player player;
    int width;
    int height;
    int x;
    int y;
    int speed;

    public Enemy(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        
        width = gp.tileSize / 2;
        height = gp.tileSize / 5;
        x = 0 - width;
        y = (int) Math.floor(Math.random() * gp.screenHeight);
        speed = 4;
    }

    public void update() {
        if (x + width >= player.x && x <= player.x + gp.tileSize && y <= player.y + gp.tileSize && y + height >= player.y) {
            return;
        }
        x += speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, width, height);
    }
}
