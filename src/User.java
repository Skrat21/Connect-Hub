import java.util.ArrayList;
import java.util.Date;

public class User {
    private String userId;
    private String email;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String status;
    private Profile profile;
    private ArrayList<User> friendList;
    private ArrayList<User> blockedList;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public User(Date dateOfBirth, String email, String password, String userId, String username, Profile profile) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.profile = profile;//Profile Management System should clone empty profile
        status = "offline";
        friendList = new ArrayList<>();
        blockedList = new ArrayList<>();
    }


    /* returns new instance of friend and block list */
    public ArrayList<User> getFriendList() {
        return new ArrayList<>(friendList);
    }

    public ArrayList<User> getBlockedList() {
        return new ArrayList<>(blockedList);
    }

    /* if function prevents duplicates, however User management should handle validation primarily */

    public void addFriend(User friend) {
        if (!friendList.contains(friend)) {
            friendList.add(friend);
        }
    }

    public void addBlockedUser(User blockedUser) {
        if (!blockedList.contains(blockedUser)) {
            blockedList.add(blockedUser);
        }
    }
    /* No exception thrown if User isn't in list */
    public void removeFriend(User friend) {
        friendList.remove(friend);
    }

    public void removeBlockedUser(User unBlockedUser) {
        blockedList.remove(unBlockedUser);
    }

    public Date getDateOfBirth() {
        return (Date) dateOfBirth.clone();//returns clone
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

}
