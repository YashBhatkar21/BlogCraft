package com.blogcraft.service;

import com.blogcraft.model.Tag;
import com.blogcraft.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public List<Tag> findByIds(List<Long> ids) {
        return tagRepository.findAllById(ids);
    }
} 