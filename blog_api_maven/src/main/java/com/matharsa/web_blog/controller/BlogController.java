package com.matharsa.web_blog.controller;

import com.matharsa.web_blog.model.BlogPost;
import com.matharsa.web_blog.repository.BlogRepository;
import com.matharsa.web_blog.model.BlogPost;
import com.matharsa.web_blog.repository.BlogRepository; // 💡 Import your repository
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class BlogController {

    // 💡 Inject your repository database connector instead of using a raw ArrayList
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;

        // Populate a default post if the database is completely empty on startup
        if (blogRepository.count() == 0) {
            blogRepository.save(new BlogPost("Web Evolution", "Cloned from console to the web!", "Tech"));

        }
    }

    @GetMapping
    public List<BlogPost> getPosts() {
        // 💡 Pull posts directly out of your actual database tables
        return blogRepository.findAll();
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post) {
        // 💡 Save the new post safely inside your persistent database
        return blogRepository.save(post);
    }
    @GetMapping("/topics")
    public List<String> getUniqueTopics() {
        return blogRepository.findAll()
                .stream()
                .map(BlogPost::getTopic)
                .filter(topic -> topic != null && !topic.trim().isEmpty())
                .distinct()
                .toList();
    }
}
