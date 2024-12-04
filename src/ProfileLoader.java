import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProfileLoader {
    // calls backend builds profile
    public static ProfileFrontEnd load(String profileJson)
    {
        Gson gson = new Gson();
        return gson.fromJson(profileJson,ProfileFrontEnd.class);
    }
}
