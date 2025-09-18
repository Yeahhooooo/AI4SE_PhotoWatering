# AI4SE_PhotoWatering

## 项目简介
本项目为命令行批量图片水印工具，自动读取图片EXIF拍摄日期并添加为水印，支持自定义字体大小、颜色和水印位置。

## 功能特性
- 支持批量处理目录下所有图片（JPG/PNG）
- 自动读取EXIF拍摄日期作为水印内容
- 可自定义字体大小、颜色和水印位置（如左上、居中、右下等）
- 处理后图片自动保存到原目录名加 _watermark 的子目录

## 使用方法
1. 编译项目：
	```shell
	javac -d out src/cli/*.java src/exif/*.java src/image/*.java src/watermark/*.java
	```
2. 运行程序：
	```shell
	java -cp out cli.Main -p <图片路径或目录> [-s 字体大小] [-c 字体颜色] [-pos 位置]
	```

## 参数说明
- `-p` 或 `--path`：必填，图片文件或目录路径
- `-s` 或 `--size`：可选，字体大小，默认32
- `-c` 或 `--color`：可选，字体颜色（如`#FFFFFF`），默认白色
- `-pos` 或 `--position`：可选，水印位置，支持：
  - top-left
  - top-center
  - top-right
  - center
  - bottom-left
  - bottom-center
  - bottom-right（默认）

## 示例
处理整个图片目录，水印居中，字体红色：
```shell
java -cp out cli.Main -p D:/photos -s 40 -c #FF0000 -pos center
```

## 依赖
- Java 8 及以上
- [metadata-extractor](https://github.com/drewnoakes/metadata-extractor)（用于EXIF读取）

## 目录结构
- src/cli/              命令行入口与参数解析
- src/exif/             EXIF信息读取
- src/image/            图片批量处理
- src/watermark/        水印生成与绘制

## 贡献与许可
欢迎学习和二次开发，遵循MIT协议。
