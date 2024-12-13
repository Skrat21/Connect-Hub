import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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
        return null;
    }

    public ArrayList<Post> readJsonFilePosts() {
        try (Reader reader = new FileReader("Posts.json")) {
            Type listType = new TypeToken<ArrayList<Content>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Post> post = gson.fromJson(reader, listType);
            return (post != null) ? post : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Story> readJsonFileStories() {
        try (Reader reader = new FileReader("Stories.json")) {
            Type listType = new TypeToken<ArrayList<Content>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<Story> story = gson.fromJson(reader, listType);
            return (story != null) ? story : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeJsonFilePosts(ArrayList<Post> posts) {
        try (Writer writer = new FileWriter("Posts.json")) {
            Gson gson = new Gson();
            gson.toJson(posts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeJsonFileStories(ArrayList<Story> stories) {
        try (Writer writer = new FileWriter("Stories.json")) {
            Gson gson = new Gson();
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
