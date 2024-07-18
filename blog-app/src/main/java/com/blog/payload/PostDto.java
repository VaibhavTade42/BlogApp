
package com.blog.payload;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	@NotEmpty
	private Long postId;
	
	@NotEmpty
	@Size(min = 4, max= 25, message="Post Title must be of min 4 characters and max of 25 characters")
	private String postTitle;
	
	@NotEmpty
	@Size(min=10, max=700, message="Content must be of min 10 characters and max of 700 characters")
	private String content;
	
	private String imageName;
	
	private Date postDate;
	
	private CategoryDto category;
	
	private UserDto user;
	

}
