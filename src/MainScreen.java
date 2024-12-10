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
    private JButton removeFriendButton;
    private JButton blockUserButton1;
    private JButton unblockUserButton1;
    private JButton showPendingInvitesButton;
    private JButton showFriendRequestsButton;

    public MainScreen(User user) {
        BackEnd backEnd = BackEnd.getInstance();
        ContentDatabase contentDatabase = ContentDatabase.getInstance();
        setTitle("Main screen");
//        FriendManagementDataBase friendManagementDataBase = FriendManagementDataBase.getInstance();
        setVisible(true);
        setSize(800, 800);
        setContentPane(panel1);
        contentPanel.add(new ContentPanel(contentDatabase.getUserPosts(user.getUserId())));//from backend list of content
//        FriendManagementData friendManagementData = friendManagementDataBase.getFriendshipData(user.getUserId());


//        for(User friend:backEnd.getUser(friendManagementDataBase.getFriendshipData(user.getUserId()).getFriendsListIds())) {
//            friendsContentPage.add(new ContentPanel(contentDatabase.getUserPosts(user.getUserId())));
        }
    }

