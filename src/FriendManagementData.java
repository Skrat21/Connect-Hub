import java.util.ArrayList;

public class FriendManagementData {
    private String userId;
    private ArrayList<String> friendsListIds;
    private ArrayList<String> blockedListIds;
    private ArrayList<String> requestListIds;
    private ArrayList<String> pendingInvitesListIds;

    public FriendManagementData(String userId) {
        this.userId = userId;
        ArrayList<String> friendsListIds = new ArrayList<>();
        ArrayList<String> blockedListIds = new ArrayList<>();
        ArrayList<String> requestListIds = new ArrayList<>();
        ArrayList<String> pendingInvitesListIds = new ArrayList<>();
    }

    public void addToFriendList(String friendId) {
        friendsListIds.add(friendId);
    }

    public void removeFromFriendList(String friendId) {
        friendsListIds.remove(friendId);
    }

    public void addToBlockedList(String friendId) {
        blockedListIds.add(friendId);
    }

    public void removeFromBlockedList(String friendId) {
        blockedListIds.remove(friendId);
    }

    public void addToRequestList(String friendId) {
        requestListIds.add(friendId);
    }

    public void removeFromRequestList(String friendId) {
        requestListIds.remove(friendId);
    }

    public void addToPendingInvitesList(String friendId) {
        pendingInvitesListIds.add(friendId);
    }

    public void removeFromPendingInvitesList(String friendId) {
        pendingInvitesListIds.remove(friendId);
    }
public String getUserId() {
        return userId;
}
}
