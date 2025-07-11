-- Insert test user for BlogCraft
USE blogcraft_db;

-- Clear existing users (optional)
DELETE FROM users WHERE username = 'admin';

-- Insert test user
INSERT INTO users (username, email, password, full_name, created_at, updated_at) 
VALUES (
    'admin', 
    'admin@blogcraft.com', 
    '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', -- This is 'admin123' encrypted with BCrypt
    'Admin User', 
    NOW(), 
    NOW()
);

-- Verify the user was created
SELECT id, username, email, full_name, created_at FROM users WHERE username = 'admin'; 