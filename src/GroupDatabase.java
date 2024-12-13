import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class GroupDatabase {

    public static void storeGroup(Group group) {
        ArrayList<Group> groups = readJsonFile();
        groups.add((Group) group);
        writeJsonFile(groups);
    }


    public static ArrayList<Group> readJsonFile() {
        try (Reader reader = new FileReader("Groups.json")) {
            Type listType = new TypeToken<ArrayList<Content>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Group> group = gson.fromJson(reader, listType);
            return (group != null) ? group : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static void writeJsonFile(ArrayList<Group> groups) {
        try (Writer writer = new FileWriter("Groups.json")) {
            Gson gson = new Gson();
            gson.toJson(groups, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGroup(Group group) {
        ArrayList<Group> groups = readJsonFile();
        groups.remove(group);
        writeJsonFile(groups);
    }

    public static Group getGroup(String id) {
        ArrayList<Group> groups = readJsonFile();
        for (Group group : groups) {
            if (group.getGroupId().equals(id)) {
                return group;
            }
        }
        return null;
    }
}

