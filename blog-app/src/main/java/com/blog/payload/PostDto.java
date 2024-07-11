
package com.blog.payload;

import java.util.Date;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private String postTitle;
	
	private String content;
	
	private String imageName;
	
	private Date postDate;
	
	private CategoryDto category;
	
	private UserDto user;
	

}
