import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private JList list1;
    private JFormattedTextField formattedTextField1;
    private JButton viewGroupButton;
    private JButton leaveGroupButton;
    private JButton joinGroupButton;
    private JButton viewProfileButton;
    private JButton blockUserButton;
    private JButton removeFriendButton;
    private JButton addFriendButton;
    private static final FriendManagement friendManagment=FriendManagement.getInstance();
    private static final UserManagement userManagement=UserManagement.getInstance();
    private final User  loggedinUser ;
    private DefaultListModel<String> listModel;
    public Search( User user) {

           loggedinUser=user;
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);

        updateFriendList("");

        // Listener for JFormattedTextField to dynamically filter friends as the user types
        formattedTextField1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterFriends();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterFriends();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterFriends();
            }
        });

        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                // Get the selected friend from the JList (username of the person to send a friend request to)
                String selectedUsername = list1.getSelectedValue().toString();

                // Call the FriendManagement logic to send a friend request
                boolean result = friendManagment.addRequest(loggedinUser.getUserId(), selectedUsername); // Using usernames
                if (result) {
                    JOptionPane.showMessageDialog(null, "Friend request sent to " + selectedUsername);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to send friend request.");
                }

            }
        });
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                String selectedUsername = list1.getSelectedValue().toString(); // Get the selected friend's username
                boolean result = friendManagment.removeFriend(loggedinUser.getUserId(), selectedUsername); // Using usernames
                if (result) {
                    JOptionPane.showMessageDialog(null, "Friend removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to remove friend.");
                }

            }
        });
        blockUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                String selectedUsername = list1.getSelectedValue().toString(); // Get the selected user's username
                boolean result =friendManagment.block(loggedinUser.getUserId(), selectedUsername); // Using usernames
                if (result) {
                    JOptionPane.showMessageDialog(null, selectedUsername + " has been blocked.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to block user.");
                }
            }
        });
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                // Display the profile or related info
                JOptionPane.showMessageDialog(null, "Viewing profile of user: " + currentUsername);

            }


        });
        joinGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = list1.getSelectedValue().toString(); // Get the selected group name from the list
                String userId = loggedinUser.getUserId();
                GroupManagement.requestJoinGroup(loggedinUser, groupName);
            }
        });
        leaveGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                String groupName = list1.getSelectedValue().toString(); // Get the selected group name
                GroupManagement.leaveGroup(currentUsername, groupName);
            }
        });
        viewGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentUsername = formattedTextField1.getText(); // Get the username from the text field
                String groupName = list1.getSelectedValue().toString(); // Get the selected group name
                JOptionPane.showMessageDialog(null, "Viewing group: " + groupName);
            }



        });

    }

    private void filterFriends() {

        String searchText = formattedTextField1.getText();
        updateFriendList(searchText);  // Update the friend list based on search query
    }

    private void updateFriendList(String filter) {
        // Retrieve the list of friends from the backend (FriendManagement)
        List<String> friends = friendManagment.getAllFriends(loggedinUser.getUserId());

        // Filter the list if a filter string is provided (from the search text)
        if (!filter.isEmpty()) {
            friends = friends.stream()
                    .filter(friend -> friend.toLowerCase().contains(filter.toLowerCase()))
                    .collect(Collectors.toList());
        }
}}
