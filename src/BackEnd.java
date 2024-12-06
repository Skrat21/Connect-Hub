import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class BackEnd {
    private static BackEnd backEndInstance = BackEnd.getInstance();
    private static final UserDatabase userDatabase = UserDatabase.getInstance();
    private static final UserCredentials userCredentials = UserCredentials.getUserCredentialsInstance();

    public  Boolean checkEmail(String email) {
        return !userCredentials.findUser(email);
    }

    public Boolean checkUsername(String username) {
        return !userDatabase.findUserName(username);
    }

    public void createUser(String email, String password, String userName, Date dateOfBirth) throws IOException, NoSuchAlgorithmException {
        User user = new User(email, userName, dateOfBirth);
        userCredentials.addUserCredentials(email, password);
        userDatabase.storeUser(user);
    }

    public Boolean validateUser(String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userCredentials.checkUserCredentials(email, password);
    }


    // singleton design pattern synchronized to be thread safe
    public static synchronized BackEnd getInstance()
    {
        if(backEndInstance==null)
        {
            backEndInstance = new BackEnd();
        }
        return backEndInstance;
    }
    private BackEnd()
    {
    }
}
