package com.blog.BlogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BlogApp.Dto.JwtAuthResponse;
import com.blog.BlogApp.Dto.JwtRequest;
import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.config.AppConstants;
import com.blog.BlogApp.entities.User;
import com.blog.BlogApp.exceptions.ApiException;
import com.blog.BlogApp.repositories.RoleRepo;
import com.blog.BlogApp.repositories.UserRepo;
import com.blog.BlogApp.security.CustomUserDetailService;
import com.blog.BlogApp.security.JwtTokenHelper;
import com.blog.BlogApp.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtRequest jwtRequest){
		this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(token, userDetails.getUsername());
		
		return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
	}
	
	private void doAuthenticate(String email, String password){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		}catch(BadCredentialsException e) {
			throw new ApiException("Invalid username or password");
		}
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto savedUserDto = userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(savedUserDto, HttpStatus.CREATED);
	}
}
