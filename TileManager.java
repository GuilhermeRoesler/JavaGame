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

        tile = new Tile[50];
        mapTileNum = new int[Constants.MAX_WORLD_COL][Constants.MAX_WORLD_ROW];

        getTileImage();
        loadMap(Utils.joinPath(Constants.MAPS_PATH, "world02.txt"));
    }

    public void getTileImage() {
        setup(0, "grass00.png", false);
        setup(1, "grass00.png", false);
        setup(2, "grass00.png", false);
        setup(3, "grass00.png", false);
        setup(4, "grass00.png", false);
        setup(5, "grass00.png", false);
        setup(6, "grass00.png", false);
        setup(7, "grass00.png", false);
        setup(8, "grass00.png", false);
        setup(9, "grass00.png", false);

        setup(10, "grass00.png", false);
        setup(11, "grass01.png", false);
        setup(12, "water00.png", true);
        setup(13, "water01.png", true);
        setup(14, "water02.png", true);
        setup(15, "water03.png", true);
        setup(16, "water04.png", true);
        setup(17, "water05.png", true);
        setup(18, "water06.png", true);
        setup(19, "water07.png", true);
        setup(20, "water08.png", true);
        setup(21, "water09.png", true);
        setup(22, "water10.png", true);
        setup(23, "water11.png", true);
        setup(24, "water12.png", true);
        setup(25, "water13.png", true);
        setup(26, "road00.png", false);
        setup(27, "road01.png", false);
        setup(28, "road02.png", false);
        setup(29, "road03.png", false);
        setup(30, "road04.png", false);
        setup(31, "road05.png", false);
        setup(32, "road06.png", false);
        setup(33, "road07.png", false);
        setup(34, "road08.png", false);
        setup(35, "road09.png", false);
        setup(36, "road10.png", false);
        setup(37, "road11.png", false);
        setup(38, "road12.png", false);
        setup(39, "earth.png", false);
        setup(40, "wall.png", true);
        setup(41, "tree.png", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, imageName)));
            tile[index].image = Utils.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int j = 0; j < Constants.MAX_WORLD_ROW; j++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");

                for (int i = 0; i < Constants.MAX_WORLD_COL; i++) {
                    mapTileNum[i][j] = Integer.parseInt(numbers[i]);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < Constants.MAX_WORLD_COL; i++) {
            for (int j = 0; j < Constants.MAX_WORLD_ROW; j++) {
                int worldX = i * gp.tileSize;
                int worldY = j * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                        worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                        worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                        worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
                    g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, null);
                }
            }
        }
    }
}
