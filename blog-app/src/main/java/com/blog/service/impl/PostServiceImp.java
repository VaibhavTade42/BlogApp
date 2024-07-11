package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post = postRepository.save(mapper.map(postDto, Post.class));
		post.setImageName("user.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = postRepository.save(post);
		
		return mapper.map(savedPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePostById(Long postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getPostByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
