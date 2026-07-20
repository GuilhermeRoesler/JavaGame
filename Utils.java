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
            return ImageIO.read(new File(joinPath(path, basename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
