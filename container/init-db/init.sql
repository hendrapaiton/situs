-- Database initialization script for SITUS application
-- This script will create tables and initial data if they don't exist

-- Create page_categories table (if it doesn't exist)
CREATE TABLE IF NOT EXISTS page_categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    category_order INTEGER DEFAULT 0,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create pages table (if it doesn't exist)
CREATE TABLE IF NOT EXISTS pages (
    id BIGSERIAL PRIMARY KEY,
    category_id BIGINT REFERENCES page_categories(id),
    title VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL UNIQUE,
    content TEXT,
    page_order INTEGER DEFAULT 0,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Add index for better performance
CREATE INDEX IF NOT EXISTS idx_page_categories_active_order ON page_categories(is_active, category_order);
CREATE INDEX IF NOT EXISTS idx_pages_active ON pages(is_active);

-- Insert initial category if it doesn't exist
INSERT INTO page_categories (name, slug, category_order, is_active, created_at, updated_at)
SELECT 'Home', 'home', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM page_categories WHERE slug = 'home');

INSERT INTO page_categories (name, slug, category_order, is_active, created_at, updated_at)
SELECT 'About', 'about', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM page_categories WHERE slug = 'about');

INSERT INTO page_categories (name, slug, category_order, is_active, created_at, updated_at)
SELECT 'Contact', 'contact', 3, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM page_categories WHERE slug = 'contact');

-- Insert initial pages if they don't exist
INSERT INTO pages (category_id, title, slug, content, page_order, is_active, created_at, updated_at)
SELECT
    (SELECT id FROM page_categories WHERE slug = 'home'),
    'Welcome to Home',
    'welcome-to-home',
    'This is the welcome content for the home page.',
    1,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM pages WHERE slug = 'welcome-to-home');

INSERT INTO pages (category_id, title, slug, content, page_order, is_active, created_at, updated_at)
SELECT
    (SELECT id FROM page_categories WHERE slug = 'about'),
    'About Us',
    'about-us',
    'This is the about us page content.',
    1,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM pages WHERE slug = 'about-us');

INSERT INTO pages (category_id, title, slug, content, page_order, is_active, created_at, updated_at)
SELECT
    (SELECT id FROM page_categories WHERE slug = 'contact'),
    'Contact Information',
    'contact-info',
    'This page contains our contact information.',
    1,
    true,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM pages WHERE slug = 'contact-info');