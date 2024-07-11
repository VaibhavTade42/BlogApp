package com.blog.service;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payload.PostDto;

public interface PostService {
	
	//create
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId);
	
	//update
	public Post updatePost(PostDto postDto, Long postId);
	
	//getPostById
	public Post getPostById(Long postId);
	
	//get all post
	public List<Post> getAllPost();
	
	//deletePostById
	public void deletePostById(Long postId);
	
	//get post by category
	public List<Post> getPostByCategory(Long categoryId);
	
	//get post by user
	public List<Post> getPostByUser(Long userId);
	
	//search post by keywords
	public List<Post> searchPost(String keyword);
	
	

}
