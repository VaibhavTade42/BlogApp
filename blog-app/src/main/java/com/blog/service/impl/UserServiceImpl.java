package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

@Service
@Transactional

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto userDto) {
	User savedUser = this.userRepository.save(mapper.map(userDto, User.class));
	
		return mapper.map(savedUser, UserDto.class);
	}
	

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
	User user = this.userRepository.findById(userId)
			 .orElseThrow(()->new ResourceNotFoundException("User"," id ",userId));
	user.setName(userDto.getName());
	user.setEmail(userDto.getEmail());
	user.setPassword(userDto.getPassword());
	user.setAbout(userDto.getAbout());
	
	User updatedUser = this.userRepository.save(user);
	
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Long userId) {
		User getUserById = this.userRepository.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User"," id ",userId));
		return mapper.map(getUserById, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		return this.userRepository.findAll()
				.stream() //Stream<Dept>
				.map(user -> mapper.map(user,UserDto.class)) //Stream <DTO>
				.collect(Collectors.toList());//List<DTO>	
				
	}

	@Override
	public void deleteUserById(Long userId) {
	User userById =	this.userRepository.findById(userId)
			              .orElseThrow(() -> new RuntimeException(
			            		  new ApiResponse("User not found with id: " + userId, false).getMessege()));
	userRepository.deleteById(userId);
		
	}

	

}
