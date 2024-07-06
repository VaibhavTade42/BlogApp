package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.User;
import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private ModelMapper mapper;
	
	@PostMapping("/save-user")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
		UserDto createUserDto = this.userService.createUser(user);
		return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/update-userById/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long userId) {
	   UserDto updatedUser = this.userService.updateUser(userDto, userId);
	   return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/get-userById/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto userDto =this.userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
				//mapper.map(user, UserDto.class);
	}
	
	@GetMapping("/getall-users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> userList = this.userService.getAllUser();
		return ResponseEntity.ok(userList);
		
	}
	
	@DeleteMapping("/delete-userById/{id}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") Long userId) {
		this.userService.deleteUserById(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted with id: "+userId, 
				true), HttpStatus.OK);
	//return new ResponseEntity(Map.of("messege", "User successfully deleted with id: "+userId), HttpStatus.OK);
		//return ResponseEntity.ok("User deleted successfully with id: "+userId);
	}

}
