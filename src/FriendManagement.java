import java.util.ArrayList;

public class FriendManagement {
    private final FriendManagementDataBase friendManagementDataBase = FriendManagementDataBase.getInstance();
    private static FriendManagement friendManagement = null;
    private static final UserDatabase userDatabase = UserDatabase.getInstance();

    private FriendManagement() {

    }


    public ArrayList<String> getAllFriends(String userId) {
        return new ArrayList<>(friendManagementDataBase.getFriendshipData(userId).getFriendsListIds());
    }

    public ArrayList<String> getAllPendingInvites(String userId) {
        return new ArrayList<>(friendManagementDataBase.getFriendshipData(userId).getPendingInvitesListIds());
    }

    public ArrayList<String> getAllBlockedUsers(String userId) {
        return new ArrayList<>(friendManagementDataBase.getFriendshipData(userId).getFriendsListIds());
    }

    public ArrayList<String> getAllFriendRequests(String userId)
    {
        return new ArrayList<>(friendManagementDataBase.getFriendshipData(userId).getRequestListIds());
    }



    public boolean addRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        if(dataOfSender.getBlockedListIds().contains(receiverId))
        {
            return false; // sender has blocked receiver
        }
        if(dataOfSender.getFriendsListIds().contains(receiverId))
        {
            return false; // user already friends;
        }

        if(dataOfSender.getPendingInvitesListIds().contains(receiverId))
        {
            return false;// already sent friend request
        }

        if(dataOfSender.getRequestListIds().contains(receiverId))
        {
            return false; // already have a friend request from receiver
        }

        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
        if (dataOfReceiver.getBlockedListIds().contains(senderId)) {
            return false; // receiver blocked sender acts as if receiver doesn't exist
        }
        dataOfSender.getPendingInvitesListIds().add(receiver.getUserId());// adds to pending invite

        dataOfReceiver.getRequestListIds().add(receiverId);
        friendManagementDataBase.modifyFriendshipData(dataOfSender);
        friendManagementDataBase.modifyFriendshipData(dataOfReceiver);
        return true;
    }

    public boolean removeFriend(String senderId,String receiverName)
    {
        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if(receiver == null)
        {
            return false;// receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        if(dataOfSender.getFriendsListIds().contains(receiverId))
        {
            // if friends
            FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
            dataOfSender.getFriendsListIds().remove(receiverId);
            dataOfReceiver.getFriendsListIds().remove(senderId);
            friendManagementDataBase.modifyFriendshipData(dataOfSender);
            friendManagementDataBase.modifyFriendshipData(dataOfReceiver);
        }
        return false;// they aren't friends
    }

    public boolean acceptRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        if (dataOfSender.getRequestListIds().contains(receiver.getUserId()))
        {
            // if there is a friend request
            String receiverId = receiver.getUserId();
            FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
            /*/removes ids from invites*/
            dataOfSender.getPendingInvitesListIds().remove(receiverId);
            dataOfReceiver.getRequestListIds().remove(senderId);
            /*/add each-other as friends*/
            dataOfSender.getFriendsListIds().add(receiverId);
            dataOfReceiver.getFriendsListIds().add(senderId);
            friendManagementDataBase.modifyFriendshipData(dataOfSender);
            friendManagementDataBase.modifyFriendshipData(dataOfReceiver);
            return true;
        }
        return false; // not in request lists
    }

    public boolean declineRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        if(dataOfSender.getRequestListIds().contains(receiverId)) {
            // if there is a friend request
            FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
            /*/removes ids from invites*/
            dataOfSender.getPendingInvitesListIds().remove(receiverId);
            dataOfReceiver.getRequestListIds().remove(senderId);
            friendManagementDataBase.modifyFriendshipData(dataOfSender);
            friendManagementDataBase.modifyFriendshipData(dataOfReceiver);
            return true;
        }
        return false;
    }

    public boolean block(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
        /*/removes ids from invites if they exist*/
        dataOfSender.getPendingInvitesListIds().remove(receiverId);
        dataOfReceiver.getRequestListIds().remove(senderId);
        /*/removes each-other as friends*/
        dataOfSender.getFriendsListIds().remove(receiverId);
        dataOfReceiver.getFriendsListIds().remove(receiverName);
        /*/ adds reciver to blocked list*/
        dataOfSender.getBlockedListIds().add(receiverId);
        friendManagementDataBase.modifyFriendshipData(dataOfSender);
        friendManagementDataBase.modifyFriendshipData(dataOfReceiver);
        return true;
    }

    public boolean unblock(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        /*/ removes receiver from blocked list*/
        dataOfSender.getBlockedListIds().remove(receiverId);
        friendManagementDataBase.modifyFriendshipData(dataOfSender);
        return true;
    }

    public synchronized static FriendManagement getInstance() {
        if (friendManagement == null) {
            friendManagement = new FriendManagement();
        }
        return friendManagement;
    }


}
