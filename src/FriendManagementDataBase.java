import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FriendManagementDataBase {
    private static FriendManagementDataBase friendManagementDataBase=null;

    private FriendManagementDataBase(){

    }

    public FriendManagementData getFriendshipData(String userId) {
        ArrayList<FriendManagementData> FriendManagementDataBase = readJsonFile();
        for (FriendManagementData data : FriendManagementDataBase) {
//            if (data.().equals(userId)) {
                return data;
            }

        return null;
    }

    public void storeFriendshipData(FriendManagementData data) {
        ArrayList<FriendManagementData> FriendManagementDataBase = readJsonFile();
        FriendManagementDataBase.add(data);
        writeJsonFile(FriendManagementDataBase);
    }
    public ArrayList<FriendManagementData> readJsonFile(){
        try (Reader reader = new FileReader("FriendManagementData.json")) {
            Type listType = new TypeToken<ArrayList<FriendManagementData>>() {}.getType();
            Gson gson = new Gson();
            ArrayList<FriendManagementData> FriendManagementDataBase = gson.fromJson(reader, listType);
            return (FriendManagementDataBase != null) ? FriendManagementDataBase : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeJsonFile(ArrayList< FriendManagementData>  FriendManagementDataBase ) {
        try (Writer writer = new FileWriter(" FriendManagementData.json")) {
            Gson gson = new Gson();
            gson.toJson(FriendManagementDataBase, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FriendManagementDataBase getInstance(){
        if(friendManagementDataBase==null){
           friendManagementDataBase=new FriendManagementDataBase();
        }
    return friendManagementDataBase;
    }
}
