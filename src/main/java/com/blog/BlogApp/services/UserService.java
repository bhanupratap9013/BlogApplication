package com.blog.BlogApp.services;

import java.util.List;

import com.blog.BlogApp.Dto.UserDto;


public interface UserService {
	UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto, int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUsers();
	void deleteUser(int userId);
}
