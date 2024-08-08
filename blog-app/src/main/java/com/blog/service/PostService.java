package com.blog.service;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	
	//create
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId);
	
	//update
	public PostDto updatePost(PostDto postDto, Long postId);
	
	//getPostById
	public PostDto getPostById(Long postId);
	
	//get all post
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	//deletePostById
	public void deletePostById(Long postId);
	
	//get post by category
	public PostResponse getPostByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	//get post by user
	public PostResponse getPostByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
	
	//search post by title
	List<PostDto> findByPostTitleContainingIgnoreCase(String keyword);

	//search post by content
	public List<PostDto> findByContentContainingIgnoreCase(String content);
	
	

}
