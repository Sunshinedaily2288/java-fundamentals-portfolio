package org.example.web_blog;

import org.example.BlogPost; // Links to your original blueprint
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class BlogController {
    private List<BlogPost> posts = new ArrayList<>();

    public BlogController() {
        // Automatically adds a post so the browser isn't empty
        posts.add(new BlogPost("Web Evolution", "Cloned from console to the web!"));
    }

    @GetMapping
    public List<BlogPost> getPosts() {
        return posts;
    }
}
