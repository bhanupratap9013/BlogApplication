package com.blog.BlogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.BlogApp.entities.Category;
import com.blog.BlogApp.entities.Post;
import com.blog.BlogApp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	public List<Post> findByTitleContaining(String title);
}
