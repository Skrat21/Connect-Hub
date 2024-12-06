import java.util.ArrayList;
// add(useNname,userName) accept block unblock suggest
public class FriendManagement {
private final BackEnd backEnd=BackEnd.getInstance();
private static  FriendManagement friendManagement=null;

  private FriendManagement(){

  }

   public boolean addRequest(String senderName,String recievererName){
   String SenderId =backEnd.getUser(senderName).getUserId();
   String RecieverId =backEnd.getUser(recievererName).getUserId();
   if()
   }
    public boolean acceptRequest(String senderName,String recievererName){
        User userSender =backEnd.getUser(senderName);
        User userReciever =backEnd.getUser(recievererName);
    }
    public boolean declineRequest(String senderName,String recievererName){
        User userSender =backEnd.getUser(senderName);
        User userReciever =backEnd.getUser(recievererName);
    }
    public boolean block(String senderName,String recievererName){
        User userSender =backEnd.getUser(senderName);
        User userReciever =backEnd.getUser(recievererName);
    }
    public boolean unblock(String senderName,String recievererName){
        User userSender =backEnd.getUser(senderName);
        User userReciever =backEnd.getUser(recievererName);
    }
    public synchronized static FriendManagement getInstance()
    {
        if( friendManagement ==null)
        {
            friendManagement = new FriendManagement();
        }
        return friendManagement;
    }


}
