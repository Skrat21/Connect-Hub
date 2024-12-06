import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MainScreen() {
        showSuggestedFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
