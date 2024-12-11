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
    private static final UserDatabase userDatabase = UserDatabase.getInstance();

    public ContentLoader(Content content) throws IOException {
        String authorId =content.getAuthorId();
        User author = userDatabase.getUser(authorId);
        BackEnd backEnd = BackEnd.getInstance();
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
        Icon profilePicture = (Icon) ImageResizer.resizeImage(author.getProfile().getProfilePhoto(), 50, 50);
        profilePictureLabel.setIcon(profilePicture);
//        for(String imagePaths: content.getImagePath())
//        {
//            //loads image from filepaths
//            Image image = null;
//            JLabel jLabelPicture = new JLabel((Icon) image);
//            imagesPanel.add(jLabelPicture);
//        }
        //refreshs content loader
        repaint();
        revalidate();
        setVisible(true);
    }
}
