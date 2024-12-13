import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.lang.reflect.Type;



public class GroupDatabase {
    private static GroupDatabase groupDatabaseInstance = null;

    public void storeGroup(Group group) {
        ArrayList<Group> groups = readJsonFile();
        groups.add(group);
        writeJsonFile(groups);
    }

    public Group getGroupById(String groupId) {
        ArrayList<Group> groups = readJsonFile();
        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) {
                return group;
            }
        }
        return null;
    }

    public ArrayList<Group> searchGroupsByName(String groupName) {
        ArrayList<Group> groups = readJsonFile();
        ArrayList<Group> matchedGroups = new ArrayList<>();
        for (Group group : groups) {
            if (group.getGroupName().contains(groupName)) {
                matchedGroups.add(group);
            }
        }
        return matchedGroups;
    }

    public ArrayList<Group> readJsonFile() {
        try (Reader reader = new FileReader("groups.json")) {
            Type listType = new TypeToken<ArrayList<Group>>() {}.getType();
            Gson gson = new Gson();
            ArrayList<Group> groups = gson.fromJson(reader, listType);
            return (groups != null) ? groups : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeJsonFile(ArrayList<Group> groups) {
        try (Writer writer = new FileWriter("groups.json")) {
            Gson gson = new Gson();
            gson.toJson(groups, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static GroupDatabase getInstance() {
        if (groupDatabaseInstance == null) {
            groupDatabaseInstance = new GroupDatabase();
        }
        return groupDatabaseInstance;
    }

    private GroupDatabase() {}
}
