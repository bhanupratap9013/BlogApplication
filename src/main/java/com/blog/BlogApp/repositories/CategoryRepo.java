package com.blog.BlogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.BlogApp.entities.Category;
import com.blog.BlogApp.entities.User;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
