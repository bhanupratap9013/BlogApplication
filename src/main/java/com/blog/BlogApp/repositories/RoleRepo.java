package com.blog.BlogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.BlogApp.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
	
}
