package com.blogcraft.controller;

import com.blogcraft.model.Post;
import com.blogcraft.model.User;
import com.blogcraft.repository.PostRepository;
import com.blogcraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("")
    public String dashboard(Model model, @RequestParam(value = "myPosts", required = false) Boolean myPosts) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findByUsername(auth.getName());
            
            if (user == null) {
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "User not found");
                model.addAttribute("errorDescription", "User data could not be retrieved from database.");
                return "error";
            }
            
            List<Post> posts;
            if (myPosts != null && myPosts) {
                posts = postRepository.findByAuthorIdOrderByCreatedAtDesc(user.getId());
            } else {
                posts = postRepository.findAll();
            }
            
            // Calculate counts for the logged-in user's posts
            List<Post> userPosts = postRepository.findByAuthorIdOrderByCreatedAtDesc(user.getId());
            long publishedCount = userPosts.stream()
                .filter(post -> "PUBLISHED".equals(post.getStatus().name()))
                .count();
            long draftCount = userPosts.stream()
                .filter(post -> "DRAFT".equals(post.getStatus().name()))
                .count();
            
            model.addAttribute("user", user);
            model.addAttribute("posts", posts);
            model.addAttribute("postCount", userPosts.size());
            model.addAttribute("publishedCount", publishedCount);
            model.addAttribute("draftCount", draftCount);
            
            return "dashboard";
        } catch (Exception e) {
            model.addAttribute("errorCode", "500");
            model.addAttribute("errorMessage", "Dashboard Error");
            model.addAttribute("errorDescription", "An error occurred while loading the dashboard: " + e.getMessage());
            return "error";
        }
    }
} 