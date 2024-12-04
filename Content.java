import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class Content {
    protected String contentId;
    protected String authorId;
    protected String content;
    protected final Date timestamp;
    protected String imgPath;

    //the constructor :)
    public Content(String contentId, String authorId, String content, String imgPath) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.imgPath = imgPath;
        this.timestamp = new Date();
    }
    //setters and getters :)
    public String getContentId() {
        return contentId;
    }
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getImagePath() {
        return imgPath;
    }
    public void setImagePath(String imgPath) {
        this.imgPath = imgPath;
    }
}

