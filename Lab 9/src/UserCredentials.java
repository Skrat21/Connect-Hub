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

public class UserCredentials {
    private static UserCredentials userCredentialsInstance = null;

    public void addUserCredentials(String email, String password) throws IOException, NoSuchAlgorithmException {
        password = PasswordHashing.hashPassword(password);

        Map<String, String> credentialsMap = readJsonFile();
        credentialsMap.put(email, password);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("Credentials.json")) {
            gson.toJson(credentialsMap, writer);
        }
    }


    public Boolean checkUserCredentials(String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> credentials = readJsonFile();
        if (credentials.containsKey(email)) {
            String pass = credentials.get(email);
            String salt = pass.substring(pass.length() - 24);
            password = PasswordHashing.hashPassword(password, salt);
            return credentials.get(email).equals(password);
        } else return false;
    }

    private Map<String, String> readJsonFile() {
        Map<String, String> credentialsMap = new HashMap<>();
        try (FileReader reader = new FileReader("Credentials.json")) {
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, String>>() {
            }.getType();
            credentialsMap = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
        return credentialsMap;
    }

    public Boolean findUser(String email) {
        Map<String, String> credentials = readJsonFile();
        return credentials.containsKey(email);
    }

    public synchronized static UserCredentials getUserCredentialsInstance() {
        if (userCredentialsInstance == null) {
            userCredentialsInstance = new UserCredentials();
        }
        return userCredentialsInstance;
    }

    private UserCredentials() {
    }

}
