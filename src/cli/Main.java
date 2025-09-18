package cli;

import exif.ExifReader;
import image.ImageProcessor;
import watermark.WatermarkGenerator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 简单命令行参数解析
        CommandLineOptions options = new CommandLineOptions();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-p":
                case "--path":
                    if (i + 1 < args.length) options.setImagePath(args[++i]);
                    break;
                case "-s":
                case "--size":
                    if (i + 1 < args.length) options.setFontSize(Integer.parseInt(args[++i]));
                    break;
                case "-c":
                case "--color":
                    if (i + 1 < args.length) options.setFontColor(args[++i]);
                    break;
                case "-pos":
                case "--position":
                    if (i + 1 < args.length) options.setPosition(args[++i]);
                    break;
                default:
                    // 忽略未知参数
            }
        }
        if (options.getImagePath() == null) {
            System.out.println("用法: java -jar PhotoWatermark.jar -p <图片路径> [-s 字体大小] [-c 字体颜色] [-pos 位置]");
            return;
        }
        // 主流程集成
        File input = new File(options.getImagePath());
        List<File> images = ImageProcessor.listImages(input);
        if (images.isEmpty()) {
            System.out.println("未找到图片文件");
            return;
        }
        File outDir = input.isDirectory() ? new File(input, input.getName() + "_watermark") : new File(input.getParentFile(), input.getName() + "_watermark");
        if (!outDir.exists()) outDir.mkdirs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (File imgFile : images) {
            BufferedImage img = ImageProcessor.readImage(imgFile);
            if (img == null) continue;
            Date date = ExifReader.getPhotoDate(imgFile);
            String watermark = date != null ? sdf.format(date) : "无拍摄日期";
            Color color;
            try {
                color = Color.decode(options.getFontColor());
            } catch (Exception e) {
                color = Color.WHITE;
            }
            BufferedImage watermarked = WatermarkGenerator.addWatermark(img, watermark, options.getFontSize(), color, options.getPosition());
            File outFile = new File(outDir, imgFile.getName());
            ImageProcessor.saveImage(watermarked, outFile);
            System.out.println("已处理: " + imgFile.getName());
        }
        System.out.println("全部处理完成，输出目录: " + outDir.getAbsolutePath());
    }
}
