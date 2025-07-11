package com.blogcraft.controller;

import com.blogcraft.model.User;
import com.blogcraft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "user_management";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("newUser") User user) {
        // Set default role if not provided
        if (user.getRole() == null) {
            user.setRole(User.Role.USER);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/role/{id}")
    public String changeUserRole(@PathVariable Long id, @RequestParam("role") String role) {
        User user = userService.findById(id);
        if (user != null) {
            user.setRole(User.Role.valueOf(role));
            userService.save(user);
        }
        return "redirect:/admin/users";
    }
} 