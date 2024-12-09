import java.util.*;


public class UserManagement {
    private static UserManagement userManagementInstance = null;
    private final static UserDatabase userDatabase = UserDatabase.getInstance();
    private final static ImageDatabase imageDatabase = ImageDatabase.getInstance();
    private final static Profile emptyProfile = new Profile("empty.jpeg","empty.jpeg","Please set your bio");
    // Add a new user to the list
    public void addUser(String email, String username, Date dateOfBirth) {
        String userId = createUserId();
        Profile emptyProfile = new Profile("empty.jpeg","empty.jpeg","Please set your bio");//for now
        User newUser = new User(userId, email, username, dateOfBirth,true,emptyProfile);
        userDatabase.storeUser(newUser);

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


    private User findUserById(String userId) {
        return userDatabase.getUser(userId);
    }


    private User findUserByUsername(String username) {
        return userDatabase.getUserUsingUsername(username);
    }


    private boolean updateUserProfile(String userId, Profile newProfile) {
        User user = findUserById(userId);
        if (user != null) {
            user.addProfile(newProfile);
            return true;
        }
        return false;
    }

    // Get user profile by userId
    public Profile getUserProfile(String userId) {
        User user = findUserById(userId);
        if (user != null) {
            return user.getProfile();//supposed to be clone
        }
        return null;
    }
    public boolean changeProfilePhoto(String userId, String imagePath) {
        User user = findUserById(userId);
        if (user != null) {
            imageDatabase.storeProfilePhoto(imagePath, userId);
            String newProfilePhotoPath = imageDatabase.getProfilePhoto(userId);
            Profile updatedProfile = user.getProfile();
            updatedProfile.setProfilePhoto(newProfilePhotoPath);  // Update the profile with the new photo
            return true;
        }
        return false;
    }
    public boolean changeCoverPhoto(String userId, String imagePath) {
        User user = findUserById(userId);
        if (user != null) {
            imageDatabase.storeCoverPhoto(imagePath, userId);
            String newCoverPhotoPath = imageDatabase.getCoverPhoto(userId);
            Profile updatedProfile = user.getProfile();
            updatedProfile.setCoverPhoto(newCoverPhotoPath);  // Update the profile with the new cover photo
            return true;
        }
        return false; // User not found
    }

    public boolean isEmailInUse(String email) {
        return userDatabase.getUserUsingEmail(email) != null;
    }
    public  boolean changeBio(String userId, String newBio) {
        User user = findUserById(userId);
        if (user != null) {
            Profile updatedProfile = user.getProfile();
            updatedProfile.setBio(newBio);  // Set the new bio
            return true;
        }
        return false;
    }
    public static synchronized UserManagement getInstance()
    {
     if(userManagementInstance==null)
     {
         return new UserManagement();
     }
     else return userManagementInstance;
    }
    private UserManagement()
    {}

}

