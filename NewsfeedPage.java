import java.util.ArrayList;

public class NewsfeedPage{

    private ArrayList<Content> contents;

    public NewsfeedPage(ArrayList<Content> contents) {
        this.contents = new ArrayList<>(contents);
    }

    public void addContent(Content content) {
        this.contents.add(content);
    }

    public ArrayList<Content> Fetch() {
        return new ArrayList<>(contents);
    }

    public ArrayList<Content> getUserContents(String Id) {
        ArrayList<Content> userContents = new ArrayList<>();
        for (int i = 0; i < contents.size(); i++) {
            Content content = contents.get(i);
            if (content.getAuthorId().equals(Id)) {
                userContents.add(content);
            }
        }
        return userContents;
    }

    public ArrayList<Content> getStories() {
        ArrayList<Content> stories = new ArrayList<>();
        for (int i = 0; i < contents.size(); i++) {
            Content content = contents.get(i);
            if (content instanceof Story) {
                Story story = (Story) content;
            if (story.IsAvailable()) {
                stories.add(content);
            }
        }
        }
        return stories;
    }
    public void removeExpiredStories() {
        for (int i = 0; i < contents.size(); i++) {
            Content content = contents.get(i);
            if (content instanceof Story) {
                Story story = (Story) content;
                if (!story.IsAvailable()) {
                contents.remove(i);
                i--; //when it will remove an element other elements will shift so -- handels this :)
            }
            }
        }
    }
    //tells if story Expired
    private boolean isExpired(Story story) {
        long time = System.currentTimeMillis();
        long contentExpiredTime = story.getExpiredTime().getTime();
        return (time!=contentExpiredTime);
    }
}
