import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TestImageResizer {
    public static BufferedImage resizeImage(String imagePath, int newWidth, int newHeight) throws IOException {
        // Read the original image from the given file path
        File inputFile = new File(imagePath);
        BufferedImage originalImage = ImageIO.read(inputFile);

        // Check if the image was loaded successfully
        if (originalImage == null) {
            throw new IOException("Failed to load image from the given path.");
        }

        // Create a new image with the specified new dimensions
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Create a new BufferedImage with the same color model as the original image
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        // Draw the scaled image into the new BufferedImage
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    // Function to save the resized image to a file
    public static void saveResizedImage(BufferedImage image, String outputPath) throws IOException {
        // Save the resized image to a new file
        File outputFile = new File(outputPath);
        ImageIO.write(image, "JPG", outputFile); // Save as JPG format
    }

    public static void main(String[] args) {
        try {
            // Define the input image path and the new dimensions
            String inputImagePath = "C:\\Users\\dell\\Desktop\\MainBefore.jpg";
            int newWidth = 800; // Define new width
            int newHeight = 600; // Define new height

            // Resize the image
            BufferedImage resizedImage = ImageResizer.loadImage(inputImagePath);

            // Define the output image path (save to Desktop)
            String outputImagePath = "C:\\Users\\dell\\Desktop\\MainAfter.jpg";

            // Save the resized image
            saveResizedImage(resizedImage, outputImagePath);

            // Print success message
            System.out.println("Image resized and saved successfully at: " + outputImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
