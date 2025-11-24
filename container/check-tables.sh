#!/bin/bash
# Script to check if tables exist in the database

echo "Waiting for PostgreSQL to be ready and tables to be initialized..."

# Wait for PostgreSQL to be fully ready
sleep 10

# Check tables in the database
echo "Checking tables in the database..."
podman exec situs-postgres psql -U postgres -d situs -c "\dt"

echo "Checking if page_categories table exists..."
podman exec situs-postgres psql -U postgres -d situs -c "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'page_categories');"