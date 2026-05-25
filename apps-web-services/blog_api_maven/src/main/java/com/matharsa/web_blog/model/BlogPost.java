package com.matharsa.web_blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String topic;
    public BlogPost() {}

    public BlogPost(String title, String content, String topic) {
        this.title = title;
        this.content = content;
        this.topic = topic;
    }

    // Id Getter and Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Title Getter and Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Content Getter and Setter
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    // Topic Getter and Setter
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
}
