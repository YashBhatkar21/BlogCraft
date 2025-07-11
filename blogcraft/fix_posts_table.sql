-- Fix posts table structure
USE blogcraft_db;

-- Drop the existing posts table
DROP TABLE IF EXISTS posts;

-- Create posts table with correct structure
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    excerpt VARCHAR(500),
    status VARCHAR(20) DEFAULT 'DRAFT',
    author_id BIGINT NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    published_at DATETIME(6),
    FOREIGN KEY (author_id) REFERENCES users(id)
);

-- Insert sample posts
INSERT INTO posts (title, content, excerpt, status, author_id, created_at, updated_at, published_at) 
VALUES (
    'Welcome to BlogCraft',
    'Welcome to BlogCraft! This is your first post. Start writing and sharing your thoughts with the world. BlogCraft provides a simple and elegant platform for writers to express themselves and connect with readers.',
    'Welcome to BlogCraft! This is your first post. Start writing and sharing your thoughts with the world.',
    'PUBLISHED',
    1,
    NOW(),
    NOW(),
    NOW()
);

INSERT INTO posts (title, content, excerpt, status, author_id, created_at, updated_at, published_at) 
VALUES (
    'Getting Started with Blogging',
    'Blogging is a powerful way to share your knowledge, experiences, and creativity. Whether you''re writing about technology, travel, cooking, or personal development, your unique perspective can inspire and help others. Start with topics you''re passionate about and write regularly to build your audience.',
    'Blogging is a powerful way to share your knowledge, experiences, and creativity. Start with topics you''re passionate about.',
    'PUBLISHED',
    1,
    NOW() - INTERVAL 1 DAY,
    NOW() - INTERVAL 1 DAY,
    NOW() - INTERVAL 1 DAY
);

INSERT INTO posts (title, content, excerpt, status, author_id, created_at, updated_at, published_at) 
VALUES (
    'My First Draft',
    'This is a draft post that I''m working on. It''s not ready for publication yet, but I''m making progress on it.',
    'This is a draft post that I''m working on. It''s not ready for publication yet.',
    'DRAFT',
    1,
    NOW(),
    NOW(),
    NULL
);

-- Verify the posts were created
SELECT id, title, status, author_id, created_at FROM posts ORDER BY created_at DESC; 