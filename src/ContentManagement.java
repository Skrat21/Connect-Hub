import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class ContentManagement {
    private final static ContentDatabase cdb = ContentDatabase.getInstance();
    private final static ImageDatabase idb = ImageDatabase.getInstance();
    private static ContentManagement contentManagementInstance = null;
    private static final ImageDatabase imageDatabase = ImageDatabase.getInstance();
    public void addContent(String[] paths, String userId, String description, String type) {
        try {
            Content content;
            String contentId = createContentId();
            idb.storeContent(paths, contentId);
            String[] newImagePaths = idb.getContentLinks(contentId);
            if (type.equals("Story")) {
                content = new Story(userId, newImagePaths, LocalDateTime.now(), LocalDateTime.now().plusDays(1), description, contentId);
            } else {
                content = new Post(userId, newImagePaths, LocalDateTime.now(), description, contentId);
            }
            cdb.storeContent(content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store content", e);
        }
    }

    public ArrayList<Content> getMyContent(String userId) {
        ArrayList<Content> contents = new ArrayList<>();
        ArrayList<Story> stories = cdb.getUserStories(userId);
        ArrayList<Post> posts = cdb.getUserPosts(userId);
        contents.addAll(stories);
        contents.addAll(posts);
        return contents;
    }

    public String[] getContentLinks(String contentId)
    {
        return imageDatabase.getContentLinks(contentId);
    }

    public void deleteContent(String contentId) throws IOException {
        idb.deleteContent(contentId);
        //cdb.deleteContent(contentId);
    }

    private String createContentId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 9;
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    public static ContentManagement getInstance() {
        if (contentManagementInstance == null) {
            contentManagementInstance = new ContentManagement();
        }
        return contentManagementInstance;
    }

    private ContentManagement() {

    }
}
