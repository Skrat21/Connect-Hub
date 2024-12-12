import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class BackEnd {
//    private static BackEnd backEndInstance = BackEnd.getInstance();
//    private static final UserDatabase userDatabase = UserDatabase.getInstance();
//    private static final UserCredentials userCredentials = UserCredentials.getUserCredentialsInstance();
//    private static final ImageDatabase contentDatabase = ImageDatabase.getInstance();
//    private static final FriendManagementDataBase friendManagementDatabase = FriendManagementDataBase.getInstance();
//    public  Boolean checkEmail(String email) {
//        return !userCredentials.findUser(email);
//    }
//
//    public Boolean checkUsername(String username) {
//        return userDatabase.getUser(username)!=null;
//    }
//
//    public void createUser(String email, String password, String userName, Date dateOfBirth) throws IOException, NoSuchAlgorithmException {
//        User user = new User(email, userName, dateOfBirth);
//        userCredentials.addUserCredentials(email, password);
//        userDatabase.storeUser(user);
//        friendManagementDatabase.storeFriendshipData(new FriendManagementData());
//    }
//
//    public User validateUser(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        if (userCredentials.checkUserCredentials(email, password)){
//            return userDatabase.getUserUsingEmail(email);
//        }
//        return null;
//    }
//
//    public User getUser(String userId){
//        return userDatabase.getUser(userId);
//    }
//
//
//    public void createContent(String userId, String[] imagePaths, String contentType, String description) throws IOException {
//        if (contentType.equals("Story")){
//            Content content = new Story(userId, imagePaths, description);
//        }
//        else{
//            Content content = new Post(userId, imagePaths, description);
//        }
//        Con
//    }
//
//
//    // singleton design pattern synchronized to be thread safe
//    public static synchronized BackEnd getInstance()
//    {
//        if(backEndInstance==null)
//        {
//            backEndInstance = new BackEnd();
//        }
//        return backEndInstance;
//    }
//    private BackEnd()
//    {
//    }
}
