import java.util.ArrayList;

public class FriendManagementData {
    private ArrayList<String> friendsListIds;
    private ArrayList<String> blockedListIds;
    private ArrayList<String> requestListIds;
    private ArrayList<String> pendingInvitesListIds;

    public FriendManagementData() {
        ArrayList<String> friendsListIds = new ArrayList<>();
        ArrayList<String> blockedListIds = new ArrayList<>();
        ArrayList<String> requestListIds = new ArrayList<>();
        ArrayList<String> pendingInvitesListIds = new ArrayList<>();
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
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
