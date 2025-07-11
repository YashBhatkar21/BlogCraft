package com.blogcraft.repository;

import com.blogcraft.model.Post;
import com.blogcraft.model.Post.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatusOrderByCreatedAtDesc(PostStatus status);
    List<Post> findByAuthorIdOrderByCreatedAtDesc(Long authorId);
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
} 