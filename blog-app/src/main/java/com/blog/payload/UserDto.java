package com.blog.payload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private Long id;
	
	//@NotNull
	@NotEmpty //=@NotNull + @NotBlank
	@Size(min=4, max = 25,message="Username must minimum of 4 characters and max 25 characters")
	private String name;
	
	@NotEmpty
	@Email(message = "Email is not valid")
	private String email;
	
	
	@NotEmpty
	@Size(min=4, max =16, message ="Password must min of 4 characters and max of 16 characters")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$&!])[A-Za-z0-9@#$&!]{4,16}$", 
	message="Password must contain at least one digit, one lowercase letter, "
			+ "one uppercase letter, and one special character from @#$&!")
	private String password;
	
	@NotEmpty
	@Size(min=10, message = "Minimum 10 characters required")
	private String about;
	
	@JsonIgnoreProperties({"user"})
	private List<PostDto> posts = new ArrayList<>();
	
	//private List<CommentDto> comments = new ArrayList<>();

}
