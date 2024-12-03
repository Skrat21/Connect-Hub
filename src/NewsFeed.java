package com.mycompany.lab9;

import java.util.ArrayList;

public class NewsFeed {

    private ArrayList<Content> contents;
//the constructor just adds new array list of contnts :)

    public NewsFeed() {
        this.contents = new ArrayList<>();
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
        if (content.getIsStoryAvilable()) {
            stories.add(content);
        }
    }
    return stories;
}
public void removeExpiredStories() {
    for (int i = 0; i < contents.size(); i++) {
        Content content = contents.get(i);
        if (content.getIsStoryAvilable() && isExpired(content)) {
            contents.remove(i);
            i--; //when it will remove an element other elements will shift so -- handels this :) 
        }
    }
}
private boolean isExpired(Content content) {
    long time = System.currentTimeMillis();
    long contentTime = content.getTimestamp().getTime();
    return (time-contentTime) > 24 * 60 * 60 * 1000; // to handle milliseconds :)
     }
}
