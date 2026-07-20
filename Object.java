import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (
            worldX > gp.player.worldX - gp.player.screenX - Constants.TILE_SIZE &&
            worldX < gp.player.worldX + gp.player.screenX + Constants.TILE_SIZE &&
            worldY > gp.player.worldY - gp.player.screenY - Constants.TILE_SIZE &&
            worldY < gp.player.worldY + gp.player.screenY + Constants.TILE_SIZE
        ) {
            g2.drawImage(image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        }
    }
}
