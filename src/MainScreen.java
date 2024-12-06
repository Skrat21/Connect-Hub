import javax.swing.*;

public class MainScreen extends JFrame {
    private JPanel panel1;
    private JButton postButton;
    private JTabbedPane tabbedPane1;
    private JButton showSuggestedFriendsButton;
    private JLabel coverPhoto;
    private JLabel userPhoto;
    private JPanel contentPanel;
    private JPanel friendsContentPage;
    private JTextPane textPane1;
    private JLabel bioTextPane;
    private JButton editBioButton;
    private JButton changeProfilePictureButton;
    private JButton changeCoverPhotoButton;
    private JButton changePasswordButton;
    private JButton addFriendButton;
    private JButton showFriendListButton;
    private JButton showBlockedListButton;
    private JButton showPendingInvitesButton;
    private JButton showFriendRequestsButton;

    public MainScreen(User user) {
        BackEnd backEnd = BackEnd.getInstance();
        ContentDatabase contentDatabase = ContentDatabase.getInstance();
        setVisible(true);
        setContentPane(panel1);
        contentPanel.add(new ContentPanel(contentDatabase));//from backend list of content
        friendsContentPage.add(new ContentPanel(contentDatabase));

    }
}
