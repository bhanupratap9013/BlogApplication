package com.blog.BlogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@PostMapping("/")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDtoCreated = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoCreated);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
		UserDto userDto = userService.getUserById(Integer.parseInt(userId));
		return ResponseEntity.status(HttpStatus.OK).body(userDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
		UserDto userDtoUpdated = userService.updateUser(userDto, Integer.parseInt(userId));
		return ResponseEntity.status(HttpStatus.OK).body(userDtoUpdated);
	}
	
	@PreAuthorize("hasRole('ADMIN ')")
	@DeleteMapping("/{userId}")
	public ResponseEntity deleteUser(@PathVariable String userId) {
		userService.deleteUser(Integer.parseInt(userId));
		return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully"); 
	}
	
}
