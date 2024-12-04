import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class userCredentials {

    public static void addUserCredentials(String email, String password) throws IOException, NoSuchAlgorithmException {
        password = PasswordHashing.hashPassword(password);

        Map<String, String> credentialsMap = readJsonFile();
        credentialsMap.put(email, password);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Credentials.json")) {
            gson.toJson(credentialsMap, writer);
        }
    }


    public static Boolean checkUserCredentials(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> credentials = readJsonFile();
        if (credentials.containsKey(email)) {
            String pass = credentials.get(email);
            String salt = pass.substring(pass.length() - 24);
            password = PasswordHashing.hashPassword(password, salt);
            return credentials.get(email).equals(password);
        }
        else return false;
    }

    private static Map<String, String> readJsonFile() {
        Map<String, String> credentialsMap = new HashMap<>();
        try (FileReader reader = new FileReader("Credentials.json")) {
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, String>>() {}.getType();
            credentialsMap = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            // If the file doesn't exist or is empty, return an empty map
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
        return credentialsMap;
    }
}
