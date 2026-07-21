import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = Constants.ORIGINAL_TILE_SIZE * Constants.TILE_SCALE;
    public final int screenWidth = tileSize * Constants.MAX_SCREEN_COL;
    public final int screenHeight = tileSize * Constants.MAX_SCREEN_ROW;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public Sound music = new Sound();
    public Sound SFX = new Sound();
    public CollisionManager collisionM = new CollisionManager(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Thread gameThread;

    public Player player = new Player(this, keyH);
    public Object obj[] = new Object[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / Constants.FPS;
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
        player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = System.nanoTime();

        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        ui.draw(g2);

        if (keyH.checkDrawTime) {
            long passedTime = System.nanoTime() - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passedTime, 10, 400);
            System.out.println("Draw Time: " + passedTime);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSFX(int i) {
        SFX.setFile(i);
        SFX.play();
    }
}
