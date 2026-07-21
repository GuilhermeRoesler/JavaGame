import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
        mapTileNum = new int[Constants.MAX_WORLD_COL][Constants.MAX_WORLD_ROW];

        getTileImage();
        loadMap(Utils.joinPath(Constants.MAPS_PATH, "world01.txt"));
    }

    public void getTileImage() {
        setup(0, "grass.png", false);
        setup(1, "wall.png", true);
        setup(2, "water.png", true);
        setup(3, "earth.png", false);
        setup(4, "tree.png", true);
        setup(5, "sand.png", false);
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
