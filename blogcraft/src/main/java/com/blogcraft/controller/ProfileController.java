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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    // Current user's profile
    @GetMapping("")
    public String myProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            model.addAttribute("errorCode", "404");
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        List<Post> published = postRepository.findByAuthorIdOrderByCreatedAtDesc(user.getId());
        model.addAttribute("profileUser", user);
        model.addAttribute("posts", published);
        model.addAttribute("isCurrentUser", true);
        model.addAttribute("user", user);
        return "profile";
    }

    // Public profile by username
    @GetMapping("/{username}")
    public String userProfile(@PathVariable String username, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(auth.getName());
        User user = userService.findByUsername(username);
        if (user == null) {
            model.addAttribute("errorCode", "404");
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        List<Post> published = postRepository.findByAuthorIdOrderByCreatedAtDesc(user.getId());
        model.addAttribute("profileUser", user);
        model.addAttribute("posts", published);
        model.addAttribute("isCurrentUser", currentUser != null && currentUser.getUsername().equals(user.getUsername()));
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @GetMapping("/edit")
    public String editProfileForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            model.addAttribute("errorCode", "404");
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        model.addAttribute("profileUser", user);
        return "edit_profile";
    }

    @PostMapping("/edit")
    public String updateProfile(@ModelAttribute("profileUser") User updatedUser, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (user == null) {
            model.addAttribute("errorCode", "404");
            model.addAttribute("errorMessage", "User not found");
            return "error";
        }
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setBio(updatedUser.getBio()); // Add bio field to User model if not present
        userService.save(user);
        return "redirect:/profile";
    }
} 