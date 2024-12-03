package com.mycompany.lab9;
import java.util.Date;

public class Content {
    private String contentId;
    private String authorId;
    private String content;
    private Date timestamp; 
    private boolean isStoryAvilable;
    private String imgPath;
    //the constructor :)
    public Content(String contentId, String authorId, String content, String imgPath, Date timestamp, boolean isStoryAvilable) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.imgPath = imgPath;
        this.timestamp = timestamp;
        this.isStoryAvilable = isStoryAvilable;
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
    public void setTimestamp(Date timestamp) { 
        this.timestamp = timestamp;
    }
    public boolean getIsStoryAvilable() { 
        return isStoryAvilable; 
    }
    public void setStory(boolean newStory) { 
        isStoryAvilable = newStory; 
    } 
    public String getImagePath() { 
        return imgPath;
    }
    public void setImagePath(String imgPath) {
        this.imgPath = imgPath;
    }
}
