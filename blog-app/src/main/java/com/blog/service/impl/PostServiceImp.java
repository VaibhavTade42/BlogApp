package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
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

	//create post with user and category
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

	//Update Post
	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	//Get Post by post id
	@Override
	public PostDto getPostById(Long postId) {
	Post postList =	postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(postList, PostDto.class);
	}

	@Override
    public List<PostDto> getAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = postList.stream()
                                            .map(post -> mapper.map(post, PostDto.class))
                                            .collect(Collectors.toList());
        return postDtoList;
    }

	//Get Post By Category id
	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
	    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

	    List<Post> postList = postRepository.findByCategory(category);

	    List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());

	    return postDtoList;
	}

	//Get Post by User id
	@Override
	public List<PostDto> getPostByUser(Long userId) {
	    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

	    List<Post> postList = postRepository.findByUser(user);

	    List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtoList;
	}


	//Search Post
	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Delete Post
	@Override
	public void deletePostById(Long postId) {
		// TODO Auto-generated method stub
		
	}

}
