import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utils {
    public static String joinPath(String path1, String path2) {
        return path1 + "/" + path2;
    }

    public static BufferedImage readImage(String path, String basename) {
        try {
            return scaleImage(ImageIO.read(new File(joinPath(path, basename))), 48, 48);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);

        return scaledImage;
    }
}
