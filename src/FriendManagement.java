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

    private boolean validRequest(String senderId, String receiverName) {
        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }

        String receiverId = receiver.getUserId();

        String reciverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);


        /*/ added for thread safety*/
        if (dataOfSender.getBlockedListIds().contains(reciverId)) {
            return false; // sender blocked user;
        }

        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(reciverId);
        if (dataOfReceiver.getBlockedListIds().contains(senderId)) {
            return false; // receiver blocked sender acts as if receiver doesn't exist
        }

        /*/ for thread safety */
        if (dataOfSender.getFriendsListIds().contains(reciverId) || dataOfReceiver.getFriendsListIds().contains(senderId)) {
            return false; //if already friends
        }

        return true;
    }


    public boolean addRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
        if (dataOfReceiver.getBlockedListIds().contains(senderId)) {
            return false; // receiver blocked sender acts as if receiver doesn't exist
        }

        dataOfSender.getPendingInvitesListIds().add(receiver.getUserId());// adds to pending invite
        return true;
    }


    public boolean acceptRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
        /*/removes ids from invites*/
        dataOfSender.getPendingInvitesListIds().remove(receiverId);
        dataOfReceiver.getRequestListIds().remove(senderId);
        /*/add each-other as friends*/
        dataOfSender.getFriendsListIds().add(receiverId);
        dataOfReceiver.getFriendsListIds().add(senderId);
        return true;
    }

    public boolean declineRequest(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        FriendManagementData dataOfReceiver = friendManagementDataBase.getFriendshipData(receiverId);
        /*/removes ids from invites*/
        dataOfSender.getPendingInvitesListIds().remove(receiverId);
        dataOfReceiver.getRequestListIds().remove(senderId);
        return true;
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
        return true;
    }

    public boolean unblock(String senderId, String receiverName) {

        User receiver = userDatabase.getUserUsingUsername(receiverName);
        if (receiver == null) {
            return false;//receiver doesn't exist
        }
        String receiverId = receiver.getUserId();
        FriendManagementData dataOfSender = friendManagementDataBase.getFriendshipData(senderId);
        /*/ removes reciver to blocked list*/
        dataOfSender.getBlockedListIds().remove(receiverId);
        return true;
    }

    public synchronized static FriendManagement getInstance() {
        if (friendManagement == null) {
            friendManagement = new FriendManagement();
        }
        return friendManagement;
    }


}
