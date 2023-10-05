package com.blog.BlogApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.config.AppConstants;
import com.blog.BlogApp.config.ConfigurationBeans;
import com.blog.BlogApp.entities.Role;
import com.blog.BlogApp.entities.User;
import com.blog.BlogApp.repositories.RoleRepo;
import com.blog.BlogApp.repositories.UserRepo;
import com.blog.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		user.getRoles().add(roleRepo.findById(AppConstants.NORMAL_USER).get());
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		User user = userRepo.findById(userId)
			    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		user.setAbout(userDto.getAbout());
		
		User updatedUser = userRepo.save(user);
		UserDto userDtoReturn = this.userToDto(updatedUser);
		return userDtoReturn;
	}

	@Override
	public UserDto getUserById(int userId) {
		User user = userRepo.findById(userId)
			    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		System.out.println(user.getPosts().size());
		UserDto userDtoReturn = this.userToDto(user);
		return userDtoReturn;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> userList = userRepo.findAll();
		
		return userList.stream()
                .map(this::userToDto)
                .collect(Collectors.toList());
	}

	@Override
	public void deleteUser(int userId) {
		User user =  userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		return modelMapper.map(userDto,User.class);
	} 
	
	public UserDto userToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		user.getRoles().add(roleRepo.findById(AppConstants.ADMIN_USER).get());
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	} 

}




