import java.util.ArrayList;

public class FriendManagementData {
    private String userId;
    private ArrayList<String> friendsListIds;
    private ArrayList<String> blockedListIds;
    private ArrayList<String> requestListIds;
    private ArrayList<String> pendingInvitesListIds;

    public FriendManagementData(String userid) {
        this.userId = userid;
        this.friendsListIds = new ArrayList<>();
        this.blockedListIds = new ArrayList<>();
        this.requestListIds = new ArrayList<>();
        this.pendingInvitesListIds = new ArrayList<>();
    }



    public ArrayList<String> getBlockedListIds() {
        return blockedListIds;
    }

    public ArrayList<String> getFriendsListIds() {
        return friendsListIds;
    }

    public ArrayList<String> getPendingInvitesListIds() {
        return pendingInvitesListIds;
    }

    public ArrayList<String> getRequestListIds() {
        return requestListIds;
    }

    public String getUserId()
    {
        return userId;
    }
}
