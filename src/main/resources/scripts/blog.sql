CREATE TABLE blog (
    id BIGSERIAL PRIMARY KEY,  -- Use BIGSERIAL for auto-incrementing IDs
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PostgreSQL does not support ON UPDATE CURRENT_TIMESTAMP directly in the column definition,
-- so we need to use a trigger for automatic updates.

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;  -- Set updated_at to the current timestamp
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER blog_updated_at
BEFORE UPDATE ON blog
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

INSERT INTO blog (title, content) VALUES
('First Post', 'Hello, My Name is Tom'),
('Second Post', 'Content 2'),
('Third Post', 'Content 3');