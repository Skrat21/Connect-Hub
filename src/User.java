import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class User {
    private String userId;
    private String email;
    private String username;
    private Date dateOfBirth;
    private Boolean status;
    private Profile profile;
    private ArrayList<User> friendList;
    private ArrayList<User> blockedList;


    public User(String email, String username, Date dateOfBirth) {
        this.email = email;
        this.username = username;
        this.userId = createUserId();
        this.dateOfBirth = dateOfBirth;
        status = false;
        friendList = new ArrayList<>();
        blockedList = new ArrayList<>();
    }

    public JsonObject getProfile(){
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        return gson.fromJson(json, JsonObject.class);
    }

    public void addProfile (Profile profile){
        this.profile = profile;
    }

    private String createUserId(){
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


}
