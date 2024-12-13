import java.util.Date;
import java.util.Random;

public class User {
    private String userId;
    private String email;
    private String username;
    private Date dateOfBirth;
    private Boolean status;
    private Profile profile;


    public User(String userId, String email, String username, Date dateOfBirth, Boolean status, Profile profile) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.profile = profile;
    }

    // to make clone
//    private User(User user)
//    {
//        this.email = user.email;
//        this.username = user.username;
//        this.dateOfBirth = (Date) user.dateOfBirth.clone();
//        this.userId = user.userId;
//        this.profile = user.profile.clone();
//        this.status = user.status;
//    }


    public Profile getProfile() {
        return this.profile;
    }

    public void addProfile(Profile profile) {
        this.profile = profile;
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

    public boolean getStatus()
    {
        return status;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    //    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return new User(this);
//    }
}

