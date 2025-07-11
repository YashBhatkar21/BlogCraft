package com.blogcraft.controller;

import com.blogcraft.model.Post;
import com.blogcraft.model.Post.PostStatus;
import com.blogcraft.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private PostRepository postRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        List<Post> publishedPosts = postRepository.findByStatusOrderByCreatedAtDesc(PostStatus.PUBLISHED);
        model.addAttribute("posts", publishedPosts);
        return "home";
    }
    
    @GetMapping("/about")
    public String about() {
        return "about";
    }
} 