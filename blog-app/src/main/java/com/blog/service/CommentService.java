package com.blog.service;

import com.blog.payload.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, Long postId);
	
	void deleteComment(Long commentId);

}
