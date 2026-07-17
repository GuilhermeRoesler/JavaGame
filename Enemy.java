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
        width = Constants.TILE_SIZE / 2;
        height = Constants.TILE_SIZE / 5;
        x = 0 - width;
        y = (int) Math.floor(Math.random() * Constants.SCREEN_HEIGHT);
        speed = 4.0;
    }

    public void update() {
        // edge collision detection
        if (x + width >= player.x && x <= player.x + Constants.TILE_SIZE && y <= player.y + Constants.TILE_SIZE && y + height >= player.y && !Constants.IS_IMORTAL) {
            gp.gameOver = true;
            return;
        }
        if (x >= Constants.SCREEN_WIDTH) {
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
