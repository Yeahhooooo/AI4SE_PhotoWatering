package watermark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WatermarkGenerator {
    public static BufferedImage addWatermark(BufferedImage srcImg, String text, int fontSize, Color color, String position) {
        BufferedImage image = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(srcImg, 0, 0, null);
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
        g2d.setColor(color);

        int x = 0, y = 0;
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getHeight();

        switch (position.toLowerCase()) {
            case "top-left":
                x = 10;
                y = textHeight;
                break;
            case "top-center":
                x = (image.getWidth() - textWidth) / 2;
                y = textHeight;
                break;
            case "top-right":
                x = image.getWidth() - textWidth - 10;
                y = textHeight;
                break;
            case "center":
                x = (image.getWidth() - textWidth) / 2;
                y = (image.getHeight() + textHeight) / 2;
                break;
            case "bottom-left":
                x = 10;
                y = image.getHeight() - 10;
                break;
            case "bottom-center":
                x = (image.getWidth() - textWidth) / 2;
                y = image.getHeight() - 10;
                break;
            case "bottom-right":
            default:
                x = image.getWidth() - textWidth - 10;
                y = image.getHeight() - 10;
                break;
        }
        g2d.drawString(text, x, y);
        g2d.dispose();
        return image;
    }
}
