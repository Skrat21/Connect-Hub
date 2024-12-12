import javax.swing.*;
import java.io.IOException;

public class ContentLoader extends JPanel {
    private JPanel panel1;
    private JLabel usernameLabel;
    private JPanel imagesPanel;
    private JTextArea contentTextArea;
    private JLabel profilePictureLabel;
    private JLabel timeLabel;
    private JLabel typeOfPostLabel;
    private static final UserManagement userManagement = UserManagement.getInstance();
    private static final ContentManagement contentManagement = ContentManagement.getInstance();
    public ContentLoader(Content content) throws IOException {
        String authorId = content.getAuthorId();
        User author = userManagement.findUserById(authorId);
        contentTextArea.setText(content.getContent());
        timeLabel.setText(content.getTimestamp().toString());
        String typeOfPost = "null";
        if (content instanceof Post) {
            typeOfPost = "Post";
        }
        if (content instanceof Story) {
            typeOfPost = "Story";
        }
        typeOfPostLabel.setText(typeOfPost);
        usernameLabel.setText(author.getUsername());
        Icon profilePicture = new ImageIcon(ImageResizer.resizeImage(author.getProfile().getProfilePhoto(), 50, 50));
        profilePictureLabel.setIcon(profilePicture);
        for(String imagePaths:contentManagement.getContentLinks(content.getContentId()) )
        {
            //loads image from filepaths
            JLabel label = new JLabel();
            label.setIcon((Icon)ImageResizer.loadImage(imagePaths));
            imagesPanel.add(label);
        }
        repaint();
        revalidate();
        setVisible(true);
    }
}
