import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainScreen extends JFrame {
    private JPanel panel1;
    private JButton postButton;
    private JTabbedPane tabbedPane1;
    private JButton showSuggestedFriendsButton;
    private JLabel coverPhoto;
    private JLabel userPhoto;
    private JPanel friendsList;
    private JTextField addFriendTextField;
    private JScrollPane contentPanel;
    private JPanel ContentPage;
    private JTextPane textPane1;
    private JLabel bioTextPane;
    private JButton editBioButton;
    private JButton changeProfilePictureButton;
    private JButton changeCoverPhotoButton;
    private JButton changePasswordButton;
    private JPanel friendsContentPage;
    private JButton addFriendButton;
    private JButton removeFriendButton;
    private JButton blockUserButton;
    private JButton unblockUserButton;
    private JButton showFriendListButton;
    private JButton showPendingInvitesButton;
    private JButton showFriendRequestsButton;
    private static final UserManagement userManagement = UserManagement.getInstance();
    private static final ContentDatabase contentDatabase = ContentDatabase.getInstance();
    private static final FriendManagement friendManagement = FriendManagement.getInstance();
    private final User  loggedinUser;
    public MainScreen(User user) throws IOException {
        setTitle("Main Screen");
        setSize(1000,800);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(panel1);
        loggedinUser = user;
        /*/
        loads everything by refreshing
         */
        refreshProfile();
        refreshNewsFeed();
        /*/Friends tab
         */

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String personToBeAdded =JOptionPane.showInputDialog("Please enter username of friend you want to add");
                if(friendManagement.addRequest(loggedinUser.getUserId(),personToBeAdded))
                {
                    JOptionPane.showMessageDialog(null,"Friend Request was Sent","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Already friends or User doesn't exist ","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendToBeRemoved = JOptionPane.showInputDialog("Please enter username of friend you want to remove");
                if(friendManagement.removeFriend(loggedinUser.getUserId(), friendToBeRemoved))
                {
                    JOptionPane.showMessageDialog(null,"Removed Friend successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Not friends or user doesn't exist","Error",JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        blockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userToBlock = JOptionPane.showInputDialog("Please enter username of the user you want to block");
                if(friendManagement.block(loggedinUser.getUserId(),userToBlock))
                {
                    JOptionPane.showMessageDialog(null,"Blocked User successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"User already blocked or doesn't exist");
                }
            }
        });
        unblockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userToUnblock = JOptionPane.showInputDialog("Please enter username of the user you want to unblock");
                if(friendManagement.unblock(loggedinUser.getUserId(),userToUnblock))
                {
                    JOptionPane.showMessageDialog(null,"User successfully unblocked","Success",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"User isn't on block list or Doesn't exist");
                }
            }
        });
        showFriendListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendList = "Username\tStatus\n\n";
                ArrayList<String> allFriendIds=friendManagement.getAllFriends(loggedinUser.getUserId());
                for(String friendId:allFriendIds)
                {
                    User friend = userManagement.findUserById(friendId);
                    friendList+= friend.getUsername();
                    friendList+="\t";
                    if(user.getStatus())
                    {
                        friendList+="Online";
                    }
                    else
                    {
                        friendList+="Offline";
                    }
                    friendList+="\n";
                }
                JOptionPane.showMessageDialog(null,friendList);
            }
        });
        showPendingInvitesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pendingInvites ="";
                ArrayList<String> allPendingInvitesIds = friendManagement.getAllPendingInvites(loggedinUser.getUserId());
                for(String pendingInvitesId:allPendingInvitesIds)
                {
                    User pendingInvitesUser = userManagement.findUserById(pendingInvitesId);
                    pendingInvites+=pendingInvitesUser.getUsername();
                    pendingInvites+="\n";
                }
                JOptionPane.showMessageDialog(null,pendingInvites);
            }
        });
        showFriendRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendRequests ="";
                ArrayList<String> allFriendRequests = friendManagement.getAllFriendRequests(loggedinUser.getUserId());
                for(String friendRequestId:allFriendRequests)
                {
                    User friendRequester = userManagement.findUserById(friendRequestId);
                    friendRequests+=friendRequester.getUsername();
                    friendRequests+="\n";
                }
                JOptionPane.showMessageDialog(null,friendRequests);
            }
        });
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPost(loggedinUser);
            }
        });
    }
    private void refreshProfile() throws IOException {
        Profile loggedinProfile = loggedinUser.getProfile();
        coverPhoto.setIcon(new ImageIcon(ImageResizer.resizeImage(
                loggedinProfile.getCoverPhoto(), 800, 400)));
        userPhoto.setIcon(new ImageIcon(ImageResizer.resizeImage(
                loggedinProfile.getProfilePhoto(), 150, 150)));
        bioTextPane.setText(loggedinProfile.getBio());
        contentPanel.removeAll();
        ArrayList<Post> userPosts = contentDatabase.getUserPosts(loggedinUser.getUserId());
        for(Content post:userPosts)
        {
            contentPanel.add(new ContentLoader(post));
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    private void refreshNewsFeed() throws IOException {
        //loads all friends newsFeeds
        friendsContentPage.removeAll();
        ArrayList<String> allFriendsId = friendManagement.getAllFriends(loggedinUser.getUserId());
        for(String friendId: allFriendsId)
        {
            User friend = userManagement.findUserById(friendId);
            ArrayList<Post> friendContent = contentDatabase.getUserPosts(friendId);
            for(Content content: friendContent)
            {
                friendsContentPage.add(new ContentLoader(content));
            }
            friendsContentPage.repaint();
            friendsContentPage.revalidate();
        }
    }
}
