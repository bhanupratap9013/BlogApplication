package com.blog.BlogApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.BlogApp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUserEmail(String userEmail);
}
