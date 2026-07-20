import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[Constants.MAX_SCREEN_COL][Constants.MAX_SCREEN_ROW];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("./img/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("./img/tiles/wall.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("./img/tiles/water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = new FileInputStream("./map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int j = 0; j < Constants.MAX_SCREEN_ROW; j++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int i = 0; i < Constants.MAX_SCREEN_COL; i++) {
                    mapTileNum[i][j] = Integer.parseInt(numbers[i]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 12; j++) {
                g2.drawImage(tile[mapTileNum[i][j]].image, i * Constants.TILE_SIZE, j * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
            }
        }
    }
}
