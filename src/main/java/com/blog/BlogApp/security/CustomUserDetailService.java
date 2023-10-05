package com.blog.BlogApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.blog.BlogApp.repositories.UserRepo;

@Component
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails userDetails = userRepo.findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email", "id"+email,0));
		return userDetails;
	}
	
}
