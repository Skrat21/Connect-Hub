import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainScreen {
    private JPanel panel1;
    private JButton postButton;
    private JTabbedPane tabbedPane1;
    private JButton showSuggestedFriendsButton;
    private JLabel coverPhoto;
    private JLabel userPhoto;
    private JPanel friendsList;
    private JTextField addFriendTextField;
    private JPanel contentPanel;
    private JPanel ContentPage;
    private JTextPane textPane1;
    private JLabel bioTextPane;
    private JButton editBioButton;
    private JButton changeProfilePictureButton;
    private JButton changeCoverPhotoButton;
    private JButton changePasswordButton;
    private static final UserDatabase userDatabase = UserDatabase.getInstance();
    private static final ContentDatabase contentDatabase = ContentDatabase.getInstance();
    private User loggedinUser;
    public MainScreen(User user) {
        loggedinUser = user;
        showSuggestedFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private void refreshProfile() throws IOException {
        Profile loggedinProfile = loggedinUser.getProfile();
        coverPhoto.setIcon((Icon) ImageResizer.loadImage(loggedinProfile.getCoverPhoto()));
        userPhoto.setIcon((Icon) ImageResizer.loadImage(loggedinProfile.getCoverPhoto()));
        bioTextPane.setText(loggedinProfile.getBio());
        contentPanel.removeAll();
        ArrayList<Content> userPosts = contentDatabase.getUserPosts(loggedinUser.getUserId());
        for(Content post:userPosts)
        {
            contentPanel.add(new ContentLoader(post));
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
