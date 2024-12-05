import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FriendListPanel extends JPanel {
    public FriendListPanel(List<Friend> friends) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (Friend friend : friends) {
            add(createFriendPanel(friend));
        }

        JScrollPane scrollPane = new JScrollPane(this);
        setPreferredSize(new Dimension(300, 400));
    }

    private JPanel createFriendPanel(Friend friend) {
        JPanel friendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel(friend.getName());
        friendPanel.add(nameLabel);

        if ("online".equals(friend.getStatus())) {
            JLabel onlineIndicator = new JLabel("•");
            onlineIndicator.setForeground(Color.GREEN);
            friendPanel.add(onlineIndicator);
        }

        return friendPanel;
}}
