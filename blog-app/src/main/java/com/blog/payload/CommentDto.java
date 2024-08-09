package com.blog.payload;



import com.blog.entities.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	
    private Long commentId;
	
	private String commentContent;
	
	//private Post post;

}
