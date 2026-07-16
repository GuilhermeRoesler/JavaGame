import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {
    GamePanel gp;
    Player player;
    int width;
    int height;
    double x;
    int y;
    double speed;

    public Enemy(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        
        width = gp.tileSize / 2;
        height = gp.tileSize / 5;
        x = 0 - width;
        y = (int) Math.floor(Math.random() * gp.screenHeight);
        speed = 4.0;
    }

    public void update() {
        if (x + width >= player.x && x <= player.x + gp.tileSize && y <= player.y + gp.tileSize && y + height >= player.y) {
            gp.gameOver = true;
            return;
        }
        if (x >= gp.screenWidth) {
            gp.score++;
            gp.enemiesToRemove.add(this);
            return;
        }

        // Sobe de forma contínua: ~+1 a cada 5s, com teto de 12
        double currentSpeed = Math.min(speed + gp.frameNum / 60.0, 20.0);
        x += currentSpeed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int) x, y, width, height);
    }
}
