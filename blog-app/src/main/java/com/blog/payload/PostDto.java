
package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blog.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	
	private Long postId;
	
	@NotEmpty
	@Size(min = 4, max= 100, message="Post Title must be of min 4 characters and max of 100 characters")
	private String postTitle;
	
	@NotEmpty
	@Size(min=10, max=700, message="Content must be of min 10 characters and max of 700 characters")
	private String content;
	
	private String imageName;
	
	private Date postDate;
	
	private CategoryDto category;
	
	//private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
	

}
