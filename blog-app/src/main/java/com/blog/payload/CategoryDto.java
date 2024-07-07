package com.blog.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
	private Long categoryId;
	
	private String categoryTitle;

	private String categoryDescription;

}
