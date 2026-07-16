import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;
    int frameNum = 0;
    int score = 0;
    boolean gameOver = false;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);
    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Enemy> enemiesToRemove = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameOver) {
            return;
        }

        if (frameNum % 60 == 0) {
            enemies.add(new Enemy(this, player));
        }

        player.update();
        for (Enemy enemy : enemies) {
            enemy.update();
        }
        enemies.removeAll(enemiesToRemove);
        enemiesToRemove.clear();

        frameNum++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);
        for (Enemy enemy : enemies) {
            enemy.draw(g2);
        }

        paintScoreText(g2);
        paintGameOverText(g2);

        g2.dispose();
    }

    public void paintScoreText(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 20, 40);
    }

    public void paintGameOverText(Graphics2D g2) {
        if (gameOver) {
            g2.setFont(new Font("Press Start 2P", Font.BOLD, 64));
            g2.setColor(Color.RED);
            String text = "GAME OVER";
            int textX = (screenWidth - g2.getFontMetrics().stringWidth(text)) / 2;
            int textY = (screenHeight - g2.getFontMetrics().getHeight()) / 2 + g2.getFontMetrics().getAscent();
            g2.drawString(text, textX, textY);
        }
    }
}
