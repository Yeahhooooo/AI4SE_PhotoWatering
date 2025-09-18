package cli;

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
        System.out.println("参数解析结果：");
        System.out.println("图片路径: " + options.getImagePath());
        System.out.println("字体大小: " + options.getFontSize());
        System.out.println("字体颜色: " + options.getFontColor());
        System.out.println("水印位置: " + options.getPosition());
        // 后续集成主流程
    }
}
