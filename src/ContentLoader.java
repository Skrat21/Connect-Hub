import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ContentLoader extends JPanel {
    private JPanel panel1;
    private JLabel usernameLabel;
    private JPanel imagesPanel;
    private JTextArea contentTextArea;
    private JLabel profilePictureLabel;
    private JLabel timeLabel;

    public ContentLoader(Content content) throws IOException {
        BackEnd backEnd = BackEnd.getInstance();
        contentTextArea.setText(content.getContent());
        User author = backEnd.getUser(content.getAuthorId());
        usernameLabel.setText(author.getUsername());
        Icon profilePicture = (Icon) ImageResizer.resizeImage(author.getProfile().getProfilePhoto(),50,50);
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
