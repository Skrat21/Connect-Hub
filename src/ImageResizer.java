import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageResizer {
    public static BufferedImage resizeImage(String imagePath, int newWidth, int newHeight) throws IOException {
        // Read the original image from the given file path
        File inputFile = new File(imagePath);
        BufferedImage originalImage = ImageIO.read(inputFile);

        // Create a new image with the specified new dimensions
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new BufferedImage from the scaled image
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }
    public static BufferedImage loadImage(String imagePath) throws IOException {
        File inputFile = new File(imagePath);

        // Check if the file exists
        if (!inputFile.exists()) {
            throw new IOException("File not found: " + imagePath);
        }

        // Attempt to read the image
        BufferedImage originalImage = ImageIO.read(inputFile);
        if (originalImage == null) {
            throw new IOException("Unsupported image format or corrupted file: " + imagePath);
        }
        // Return the original image without resizing
        return originalImage;
    }
   }
