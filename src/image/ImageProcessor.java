package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ImageProcessor {

    public static List<File> listImages(File dir) {
        List<File> images = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    images.addAll(listImages(file));
                } else if (isImageFile(file)) {
                    images.add(file);
                }
            }
        } else if (isImageFile(dir)) {
            images.add(dir);
        }
        return images;
    }

    private static boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }


    public static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            return null;
        }
    }

    public static void saveImage(BufferedImage img, File outFile) {
        try {
            String ext = "jpg";
            String name = outFile.getName().toLowerCase();
            if (name.endsWith(".png")) ext = "png";
            ImageIO.write(img, ext, outFile);
        } catch (IOException e) {
            // 保存失败
        }
    }
}
