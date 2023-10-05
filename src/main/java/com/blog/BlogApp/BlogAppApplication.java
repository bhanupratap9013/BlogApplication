package com.blog.BlogApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.blog.BlogApp.config.AppConstants;
import com.blog.BlogApp.entities.Role;
import com.blog.BlogApp.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
		
	}
	
	@Bean
	CommandLineRunner run(RoleRepo roleRepo) {
		return args -> {
			if (roleRepo==null || roleRepo.count()==0) {
		        Role roleAdmin = new Role();
		        roleAdmin.setId((long)AppConstants.ADMIN_USER);
		        roleAdmin.setName("ROLE_ADMIN");

		        Role roleUser = new Role();
		        roleUser.setId((long)AppConstants.NORMAL_USER);
		        roleUser.setName("ROLE_USER");

		        roleRepo.save(roleAdmin);
		        roleRepo.save(roleUser);
		    }
		};
	}
	
	}

