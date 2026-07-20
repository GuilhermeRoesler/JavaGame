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
        mapTileNum = new int[Constants.MAX_WORLD_COL][Constants.MAX_WORLD_ROW];

        getTileImage();
        loadMap(Utils.joinPath(Constants.MAPS_PATH, "world01.txt"));
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "wall.png")));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "water.png")));
            tile[2].collision = true;
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "earth.png")));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "tree.png")));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File(Utils.joinPath(Constants.TILES_PATH, "sand.png")));
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
                int worldX = i * Constants.TILE_SIZE;
                int worldY = j * Constants.TILE_SIZE;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (
                    worldX > gp.player.worldX - gp.player.screenX - Constants.TILE_SIZE &&
                    worldX < gp.player.worldX + gp.player.screenX + Constants.TILE_SIZE &&
                    worldY > gp.player.worldY - gp.player.screenY - Constants.TILE_SIZE &&
                    worldY < gp.player.worldY + gp.player.screenY + Constants.TILE_SIZE
                ) {
                    g2.drawImage(tile[mapTileNum[i][j]].image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
                }
            }
        }
    }
}
