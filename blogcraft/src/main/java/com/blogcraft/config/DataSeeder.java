package com.blogcraft.config;

import com.blogcraft.model.Category;
import com.blogcraft.model.Tag;
import com.blogcraft.repository.CategoryRepository;
import com.blogcraft.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... args) {
        List<String> categories = Arrays.asList("Technology", "Lifestyle", "Education", "Health", "Business", "Travel", "Food", "Entertainment", "Science", "Sports");
        for (String name : categories) {
            if (categoryRepository.findByName(name) == null) {
                categoryRepository.save(new Category(null, name, null));
            }
        }
        List<String> tags = Arrays.asList("Java", "Spring Boot", "Productivity", "Fitness", "Finance", "Recipes", "Movies", "Research", "Tutorials", "Reviews");
        for (String name : tags) {
            if (tagRepository.findByName(name) == null) {
                tagRepository.save(new Tag(null, name, null));
            }
        }
    }
} 