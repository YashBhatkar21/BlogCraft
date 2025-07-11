-- Insert sample posts for BlogCraft
USE blogcraft_db;

-- Clear existing posts (optional)
DELETE FROM posts;

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

INSERT INTO posts (title, content, excerpt, status, author_id, created_at, updated_at) 
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