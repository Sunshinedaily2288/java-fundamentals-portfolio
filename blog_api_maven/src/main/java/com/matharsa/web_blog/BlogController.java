
package com.matharsa.web_blog;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class BlogController {

    private final List<BlogPost> posts = new ArrayList<>();

    public BlogController() {
        posts.add(new BlogPost("Web Evolution", "Cloned from console to the web!"));
    }

    @GetMapping
    public List<BlogPost> getPosts() {
        return posts;
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post) {
        posts.add(post);
        return post;
    }
}
