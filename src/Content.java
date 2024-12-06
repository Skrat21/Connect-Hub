import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public abstract class Content {
    protected String contentId;
    protected String authorId;
    protected final LocalDateTime timestamp;
    protected  String content;

    public Content(String authorId, String[] imgPaths,String content) throws IOException {
        this.contentId = createContentId();
        this.authorId = authorId;
        this.timestamp = LocalDateTime.now();
        this.content = content;
        ContentDatabase.storeContent(imgPaths, this.contentId);
    }

    public String createContentId(){
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

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getContentId() {
        return contentId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

