package org.example.web_blog;

import java.time.LocalDateTime;

public class BlogPost {
    private String title;
    private String content;
    private LocalDateTime date;

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now();
    }

    // ADD THESE SO SPRING CAN READ THE DATA
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getDate() { return date; }

    @Override
    public String toString() {
        return "[" + date + "] " + title.toUpperCase() + "\n" + content;
    }
}



