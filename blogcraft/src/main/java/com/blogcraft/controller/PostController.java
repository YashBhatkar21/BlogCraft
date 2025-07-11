package com.blogcraft.controller;

import com.blogcraft.model.Post;
import com.blogcraft.model.User;
import com.blogcraft.model.Post.PostStatus;
import com.blogcraft.service.PostService;
import com.blogcraft.service.UserService;
import com.blogcraft.service.CategoryService;
import com.blogcraft.service.TagService;
import com.blogcraft.model.Category;
import com.blogcraft.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @GetMapping("/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("statuses", PostStatus.values());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("tags", tagService.findAll());
        return "post_form";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute Post post, @RequestParam Long categoryId, @RequestParam(required = false) List<Long> tagIds, @AuthenticationPrincipal UserDetails userDetails) {
        // Set author from userDetails
        if (userDetails != null) {
            String username = userDetails.getUsername();
            post.setAuthor(userService.findByUsername(username));
        }
        // Set category
        post.setCategory(categoryService.findAll().stream().filter(c -> c.getId().equals(categoryId)).findFirst().orElse(null));
        // Set tags
        if (tagIds != null) {
            post.setTags(tagService.findByIds(tagIds));
        }
        // Generate excerpt (first 200 chars)
        if (post.getContent() != null) {
            String excerpt = post.getContent().length() > 200 ? post.getContent().substring(0, 200) + "..." : post.getContent();
            post.setExcerpt(excerpt);
        }
        // Set publishedAt if status is PUBLISHED
        if (post.getStatus() == Post.PostStatus.PUBLISHED) {
            post.setPublishedAt(java.time.LocalDateTime.now());
        }
        postService.savePost(post);
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String showEditPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        if (post == null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("post", post);
        model.addAttribute("statuses", PostStatus.values());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("tags", tagService.findAll());
        return "post_form";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post, @RequestParam Long categoryId, @RequestParam(required = false) List<Long> tagIds, @AuthenticationPrincipal UserDetails userDetails) {
        Post existing = postService.findById(id);
        if (existing == null) {
            return "redirect:/dashboard";
        }
        // Only allow editing by the author
        if (userDetails != null && existing.getAuthor().getUsername().equals(userDetails.getUsername())) {
            existing.setTitle(post.getTitle());
            existing.setContent(post.getContent());
            existing.setStatus(post.getStatus());
            // Update category
            existing.setCategory(categoryService.findAll().stream().filter(c -> c.getId().equals(categoryId)).findFirst().orElse(null));
            // Update tags
            if (tagIds != null) {
                existing.setTags(tagService.findByIds(tagIds));
            } else {
                existing.setTags(null);
            }
            // Update excerpt
            if (post.getContent() != null) {
                String excerpt = post.getContent().length() > 200 ? post.getContent().substring(0, 200) + "..." : post.getContent();
                existing.setExcerpt(excerpt);
            }
            // Update publishedAt if status is PUBLISHED
            if (post.getStatus() == Post.PostStatus.PUBLISHED && existing.getPublishedAt() == null) {
                existing.setPublishedAt(java.time.LocalDateTime.now());
            }
            postService.savePost(existing);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Post post = postService.findById(id);
        if (post != null && userDetails != null && post.getAuthor().getUsername().equals(userDetails.getUsername())) {
            postService.deleteById(id);
        }
        return "redirect:/dashboard";
    }
} 