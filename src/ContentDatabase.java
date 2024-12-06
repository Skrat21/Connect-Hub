import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ContentDatabase {
    private static ContentDatabase contentDatabaseInstance = null;

    public void storeContent(String[] imagePaths, String contentId) throws IOException {
        File contentDatabaseFolder = new File("Content Database");
        if (!contentDatabaseFolder.exists()) {
            contentDatabaseFolder.mkdir();
        }

        File contentFolder = new File(contentDatabaseFolder, contentId);
        if (!contentFolder.exists()) {
            contentFolder.mkdir();
        }

        for (String imagePath : imagePaths) {
            File sourceFile = new File(imagePath);
            if (!sourceFile.exists()) {
                continue;
            }

            BufferedImage image = ImageIO.read(sourceFile);

            String newFileName = sourceFile.getName();
            File targetFile = new File(contentFolder, newFileName);

            String fileExtension = getFileExtension(sourceFile.getName());
            if (fileExtension == null) {
                continue;
            }

            ImageIO.write(image, fileExtension, targetFile);
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

    public void deleteContent(String contentId) throws IOException {
        File contentDatabase = new File("Content Database");
        File contentFolder = new File(contentDatabase, contentId);

        if (!contentFolder.exists() || !contentFolder.isDirectory()) {
            return;
        }

        File[] files = contentFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        contentFolder.delete();
    }

    public ContentDatabase getInstance() {
        if (contentDatabaseInstance == null) {
            contentDatabaseInstance = new ContentDatabase();
        }
        return contentDatabaseInstance;
    }

    private ContentDatabase() {
    }
}


