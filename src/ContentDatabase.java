import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContentDatabase {
    private static ContentDatabase contentDatabaseInstance = null;
    public void storeContent(Content content) {
        ArrayList<Content> contents = readJsonFile();
        contents.add(content);
        writeJsonFile(contents);
    }

    public Content getContent(String contentId) {
        ArrayList<Content> content = readJsonFile();
        for (Content contents : content) {
            if (contents.getContentId().equals(contentId)) {
                return contents;
            }
        }
        return null;
    }

    public ArrayList<Content> getUserPosts(String userId){
        ArrayList<Content> content = readJsonFile();
        ArrayList<Content> userPosts = new ArrayList<>();
        for (Content contents : content) {
            if (contents.getAuthorId().equals(userId)) {
                userPosts.add(contents);
            }
        }
        return userPosts;
    }

    public ArrayList<Content> readJsonFile(){
        try (Reader reader = new FileReader("Content.json")) {
            Type listType = new TypeToken<ArrayList<Content>>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            ArrayList<Content> content = gson.fromJson(reader, listType);
            return (content != null) ? content : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeJsonFile(ArrayList<Content> content) {
        try (Writer writer = new FileWriter("Content.json")) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            gson.toJson(content, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //singleton design pattern
    public synchronized static ContentDatabase getInstance()
    {
        if(contentDatabaseInstance==null)
        {
            contentDatabaseInstance = new ContentDatabase();
        }
        return contentDatabaseInstance;
    }
    private ContentDatabase() {

    }


}
