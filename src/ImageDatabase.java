import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageDatabase {
    private static ImageDatabase contentDatabaseInstance = null;

    public void storeContent(String[] imagePaths, String contentId) throws IOException {
        File contentDatabaseFolder = new File("ContentImages Database");
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

    public void storeCoverPhoto(String imagePath,String userId)
    {
        File contentDatabaseFolder = new File("CoverPhoto Database");
        if (!contentDatabaseFolder.exists()) {
            contentDatabaseFolder.mkdir();
        }

        File sourceFile = new File(imagePath);
        try {
            BufferedImage image = ImageIO.read(sourceFile);
            String newFileName = sourceFile.getName();
            File targetFile = new File(userId, newFileName);
            String fileExtension = getFileExtension(sourceFile.getName());
            ImageIO.write(image, Objects.requireNonNull(fileExtension), targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void storeProfilePhoto(String imagePath , String userId)
    {
        File contentDatabaseFolder = new File("ProfilePhoto Database");
        if (!contentDatabaseFolder.exists()) {
            contentDatabaseFolder.mkdir();
        }

        File sourceFile = new File(imagePath);
        try {
            BufferedImage image = ImageIO.read(sourceFile);
            String newFileName = sourceFile.getName();
            File targetFile = new File(userId, newFileName);
            String fileExtension = getFileExtension(sourceFile.getName());
            ImageIO.write(image, Objects.requireNonNull(fileExtension), targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getContentLinks(String contentId)
    {
        File folder = new File("ContentImages Database\\"+contentId);
        File[] listOfFiles = folder.listFiles();

        String[] filePaths = new String[Objects.requireNonNull(listOfFiles).length];
        for (int i = 0; i < listOfFiles.length; i++) {
            filePaths[i] = listOfFiles[i].getAbsolutePath();
        }
        return filePaths;
    }

    public String getProfilePhoto(String userId)
    {
        File folder = new File("ProfilePhoto Database\\"+userId);
        return folder.getAbsolutePath();
    }

    public String getCoverPhoto(String userId)
    {
        File folder = new File("CoverPhoto Database\\"+userId);
        return folder.getAbsolutePath();
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


    public static ImageDatabase getInstance() {
        if (contentDatabaseInstance == null) {
            contentDatabaseInstance = new ImageDatabase();
        }
        return contentDatabaseInstance;
    }

    private ImageDatabase() {
    }
}


