import java.util.Date;
import java.util.Random;

public class User {
    private String userId;
    private String email;
    private String username;
    private Date dateOfBirth;
    private Boolean status;
    private Profile profile;


    public User(String email, String username, Date dateOfBirth) {
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.userId = createUserId();
        this.profile = null;// empty Profile
        this.status = false;
    }
    // to make clone
    private User(User user)
    {
        this.email = user.email;
        this.username = user.username;
        this.dateOfBirth = (Date) user.dateOfBirth.clone();
        this.userId = user.userId;
        this.profile = user.profile.clone();
        this.status = user.status;
    }


    public Profile getProfile() {
        return this.profile;
    }

    public void addProfile(Profile profile) {
        this.profile = profile;
    }

    private static String createUserId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 9;
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new User(this);
    }
}

