package com.blog.payload;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private int totalPages;
	
	private long totalElements;
	
	private boolean isLastPage;
	

}
