package com.blogcraft.config;

import com.blogcraft.model.Post;
import com.blogcraft.model.User;
import com.blogcraft.repository.PostRepository;
import com.blogcraft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Only initialize if no users exist
        if (userRepository.count() == 0) {
            initializeData();
        }
    }
    
    private void initializeData() {
        // Create a test user
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@blogcraft.com");
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setFullName("Admin User");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        
        // Create sample posts
        Post post1 = new Post();
        post1.setTitle("Welcome to BlogCraft");
        post1.setContent("Welcome to BlogCraft! This is your first post. Start writing and sharing your thoughts with the world. BlogCraft provides a simple and elegant platform for writers to express themselves and connect with readers.");
        post1.setExcerpt("Welcome to BlogCraft! This is your first post. Start writing and sharing your thoughts with the world.");
        post1.setStatus(Post.PostStatus.PUBLISHED);
        post1.setAuthor(savedUser);
        post1.setCreatedAt(LocalDateTime.now());
        post1.setUpdatedAt(LocalDateTime.now());
        post1.setPublishedAt(LocalDateTime.now());
        
        Post post2 = new Post();
        post2.setTitle("Getting Started with Blogging");
        post2.setContent("Blogging is a powerful way to share your knowledge, experiences, and creativity. Whether you're writing about technology, travel, cooking, or personal development, your unique perspective can inspire and help others. Start with topics you're passionate about and write regularly to build your audience.");
        post2.setExcerpt("Blogging is a powerful way to share your knowledge, experiences, and creativity. Start with topics you're passionate about.");
        post2.setStatus(Post.PostStatus.PUBLISHED);
        post2.setAuthor(savedUser);
        post2.setCreatedAt(LocalDateTime.now().minusDays(1));
        post2.setUpdatedAt(LocalDateTime.now().minusDays(1));
        post2.setPublishedAt(LocalDateTime.now().minusDays(1));
        
        postRepository.save(post1);
        postRepository.save(post2);
        
        System.out.println("Sample data initialized successfully!");
        System.out.println("Username: admin");
        System.out.println("Password: admin123");
    }
} 