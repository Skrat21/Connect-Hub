import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ContentDatabase {
    private static ContentDatabase contentDatabaseInstance = null;

    public void storeContent(Content content) {
        if (content instanceof Post) {
            ArrayList<Post> posts = readJsonFilePosts();
            posts.add((Post) content);
            writeJsonFilePosts(posts);
        }
        if (content instanceof Story) {
            ArrayList<Story> stories = readJsonFileStories();
            stories.add((Story) content);
            writeJsonFileStories(stories);
        }
    }

//    public Content getContent(String contentId) {
//        ArrayList<Content> contents = readJsonFile();
//        for (Content content : contents) {
//            if (content.getContentId().equals(contentId)) {
//                return content;
//            }
//        }
//        return null;
//    }

    public Post getPost(String contentId) {
        ArrayList<Post> posts = readJsonFilePosts();
        for (Post post : posts) {
            if (post.getContentId().equals(contentId)) {
                return post;
            }
        }
        return null;
    }

    public Story getStory(String contentId){
        ArrayList<Story> stories = readJsonFileStories();
        for(Story story: stories)
        {
            if(story.getContentId().equals(contentId))
            {
                return story;
            }
        }
        return null;
    }

    public ArrayList<Post> getUserPosts(String userId) {
        ArrayList<Post> posts = readJsonFilePosts();
        ArrayList<Post> userPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAuthorId().equals(userId)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }
    public ArrayList<Story> getUserStories(String userId)
    {
        ArrayList<Story> stories = readJsonFileStories();
        ArrayList<Story> userStories = new ArrayList<>();
        for(Story story: stories)
        {
            if(story.getAuthorId().equals(userId))
            {
                userStories.add(story);
            }
        }
        return userStories;
    }

    public ArrayList<Post> readJsonFilePosts() {
        File file = new File("Posts.json");
        if (file.length() == 0) {
            // File exists but is empty
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Post>>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            ArrayList<Post> posts = gson.fromJson(reader, listType);
            return (posts != null) ? posts : new ArrayList<>();
        } catch (FileNotFoundException e) {
            // File does not exist
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            // Invalid JSON content
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public ArrayList<Story> readJsonFileStories() {
        File file = new File("Stories.json");
        if (file.length() == 0) {
            // File exists but is empty
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Story>>() {}.getType();
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            ArrayList<Story> stories = gson.fromJson(reader, listType);
            return (stories != null) ? stories : new ArrayList<>();
        } catch (FileNotFoundException e) {
            // File does not exist
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            // Invalid JSON content
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private void writeJsonFilePosts(ArrayList<Post> posts) {
        try (Writer writer = new FileWriter("Posts.json")) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJsonFileStories(ArrayList<Story> stories) {
        try (Writer writer = new FileWriter("Stories.json")) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            gson.toJson(stories, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //singleton design pattern
    public synchronized static ContentDatabase getInstance() {
        if (contentDatabaseInstance == null) {
            contentDatabaseInstance = new ContentDatabase();
        }
        return contentDatabaseInstance;
    }

    private ContentDatabase() {

    }


}
