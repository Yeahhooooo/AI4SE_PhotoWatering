package exif;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class ExifReader {
    public static Date getPhotoDate(File imageFile) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (directory != null) {
                Date date = directory.getDateOriginal();
                return date;
            }
        } catch (Exception e) {
            // 读取失败返回null
        }
        return null;
    }
}
