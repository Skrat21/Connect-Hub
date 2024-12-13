import java.time.LocalDateTime;

public class NotificationsManagement {
public final static NotificationsDatabase ndb=NotificationsDatabase.getInstance();
public final static UserDatabase udb=UserDatabase.getInstance();
public Notifications addRequest(String recieverName,String senderId){
    String message=udb.getUser(senderId).getUsername()+" sent you a friend request";
    Notifications add=new Notifications(message,udb.getUser(recieverName).getUserId(), LocalDateTime.now());
    ndb.storeNotifications(add);
    return add;
}
public Notifications acceptedRequest(String recieverName,String senderId){
    String message=recieverName+" accepted your friend request";
    Notifications accepted=new Notifications(message,senderId,LocalDateTime.now());
    ndb.storeNotifications(accepted);
    return accepted;
}
public Notifications addedPost(String authorId){
        String message=udb.getUserUsingUsername(authorId).getUsername()+" added post";
        Notifications addedPost=new Notifications(message,authorId,LocalDateTime.now());
        ndb.storeNotifications(addedPost);
        return addedPost;
    }
   /*
    public Notifications addedMember(String memberName){
        String message=memberName+" Joined the group";
        Notifications addedPost=new Notifications(message,authorId,LocalDateTime.now());
        ndb.storeNotifications(addedPost);
        return addedPost;
    }
    *///
}
