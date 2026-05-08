package org.example;

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

    @Override
    public String toString() {
        return "[" + date + "] " + title.toUpperCase() + "\n" + content;
    }
}


