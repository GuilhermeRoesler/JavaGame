import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Entity {
    GamePanel gp;
    Player player;
    double x;
    double speed;

    public Enemy(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        
        setDefaultValues();
    }
    
    public void setDefaultValues() {
        width = gp.tileSize / 2;
        height = gp.tileSize / 5;
        x = 0 - width;
        y = (int) Math.floor(Math.random() * gp.screenHeight);
        speed = 4.0;
    }

    public void update() {
        // edge collision detection
        if (x + width >= player.x && x <= player.x + gp.tileSize && y <= player.y + gp.tileSize && y + height >= player.y && !Constants.IS_IMORTAL) {
            gp.gameOver = true;
            return;
        }
        if (x >= gp.screenWidth) {
            gp.score++;
            gp.enemiesToRemove.add(this);
            return;
        }

        // gradative speed increase
        double currentSpeed = Math.min(speed + gp.frameNum / Constants.SPEED_INCREASE_RATE, Constants.MAX_SPEED);
        x += currentSpeed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect((int) x, y, width, height);
    }
}
