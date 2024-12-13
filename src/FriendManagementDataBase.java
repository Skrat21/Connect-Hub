import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FriendManagementDataBase {
    private static  FriendManagementDataBase friendManagementDataBaseInstance=null;

    private FriendManagementDataBase(){

    }

    public FriendManagementData getFriendshipData(String userId) {
        ArrayList<FriendManagementData> friendManagementDataBase = readJsonFile();
        for (FriendManagementData data : friendManagementDataBase) {
            if (data.getUserId().equals(userId)) {
                return data;
            }
        }

        return null;
    }

    public void storeFriendshipData(FriendManagementData data) {
        ArrayList<FriendManagementData> friendManagementDataBase = readJsonFile();
        friendManagementDataBase.add(data);
        writeJsonFile(friendManagementDataBase);
    }

    public void modifyFriendshipData(FriendManagementData data)
    {
        removeFriendshipData(data);
        storeFriendshipData(data);
    }

    public void removeFriendshipData(FriendManagementData Data)
    {
        String id = Data.getUserId();
        FriendManagementData oldData = null;
        ArrayList<FriendManagementData> friendManagementDatabase = readJsonFile();
        for(FriendManagementData friendManagementData:friendManagementDatabase)
        {
            if(friendManagementData.getUserId().equals(id))
            {
                oldData = friendManagementData;
                break;
            }
        }
        if(oldData!=null)
        {
            friendManagementDatabase.remove(oldData);
            writeJsonFile(friendManagementDatabase);
        }
    }

    public ArrayList<FriendManagementData> readJsonFile(){
        try (Reader reader = new FileReader("D:\\Programming 2\\Lab9\\ FriendManagementData.json")) {
            Type listType = new TypeToken<ArrayList<FriendManagementData>>() {}.getType();
            Gson gson = new Gson();
            ArrayList<FriendManagementData> FriendManagementDataBase = gson.fromJson(reader, listType);
            return (FriendManagementDataBase != null) ? FriendManagementDataBase : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.out.println("failllll");
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeJsonFile(ArrayList< FriendManagementData>  FriendManagementDataBase ) {
        try (Writer writer = new FileWriter("FriendManagementData.json")) {
            Gson gson = new Gson();
            gson.toJson(FriendManagementDataBase, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FriendManagementDataBase getInstance(){
        if(friendManagementDataBaseInstance==null){
            friendManagementDataBaseInstance=new FriendManagementDataBase();
        }
    return friendManagementDataBaseInstance;
    }
}
