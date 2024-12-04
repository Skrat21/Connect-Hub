import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class userDatabase {
    public static void storeUser(User user) {
        ArrayList<User> users = readJsonFile();
        users.add(user);
        writeJsonFile(users);
    }

    public static User getUser(String userId) {
        ArrayList<User> users = readJsonFile();
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public static Boolean findUserName(String userName){
        ArrayList<User> users = readJsonFile();
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<User> readJsonFile(){
        try (Reader reader = new FileReader("users.json")) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            Gson gson = new Gson();
            ArrayList<User> users = gson.fromJson(reader, listType);
            return (users != null) ? users : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void writeJsonFile(ArrayList<User> users) {
        try (Writer writer = new FileWriter("users.json")) {
            Gson gson = new Gson();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}