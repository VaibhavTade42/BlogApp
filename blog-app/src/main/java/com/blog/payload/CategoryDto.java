package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
	private Long categoryId;
	
	@Size(min = 4, max=30, message="Title must be min of 4 characcters and max of 30 characters")
	@NotEmpty(message= "Category Title must not be Empty or null")
	private String categoryTitle;
    
	@Size(min=10, max=100, message = "description must be min 10 characters and max 100 characters")
	@NotEmpty(message= "Category Title must not be Empty or null")
	private String categoryDescription;

}
