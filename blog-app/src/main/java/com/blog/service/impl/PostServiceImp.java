package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
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
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepository.save(post);
		return mapper.map(updatedPost, PostDto.class);
	}

	//Get Post by post id
	@Override
	public PostDto getPostById(Long postId) {
	Post postList =	postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(postList, PostDto.class);
	}

	
	//Get all Post
	@Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		
		Sort sort = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		                                                       //Sort.by(sortBy).descending()
		
        Page<Post> pagePost = postRepository.findAll(pageable);
                              
        List<Post> allPosts = pagePost.getContent(); 
        
        List<PostDto> postDtoList = allPosts.stream()
                    .map(post -> mapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        
        return postResponse;
    }

	//Get Post By Category id
	@Override
	public PostResponse getPostByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
	    Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

	    Sort sort = null;
		if(sortDirection.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		                                                       //Sort.by(sortBy).descending()
		
        Page<Post> pagePost = postRepository.findByCategory(category, pageable);
                              
        List<Post> allPosts = pagePost.getContent(); 
        
        List<PostDto> postDtoList = allPosts.stream()
                    .map(post -> mapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        
        return postResponse;
	    
	    
//	    List<Post> postList = postRepository.findByCategory(category);
//
//	    List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
//
//	    return postDtoList;
	}

	//Get Post by User id
	@Override
    public PostResponse getPostByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Sort sort = Sort.by(sortBy);
        sort = sortDirection.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findByUser(user, pageable);

        List<PostDto> postDtoList = pagePost.getContent().stream()
                .map(post -> mapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
	    
//	    List<Post> postList = postRepository.findByUser(user);
//
//	    List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
//	    return postDtoList;
	}


	
	
	//Delete Post
	@Override
	public void deletePostById(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		postRepository.delete(post);
		
	}

	//search post by title
	@Override
	public List<PostDto> findByPostTitleContainingIgnoreCase(String keyword) {
		
		 List<Post> postList =  postRepository.findByPostTitleContainingIgnoreCase(keyword);
		 List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		    return postDtoList;
	}

	//search post by content
	@Override
	public List<PostDto> findByContentContainingIgnoreCase(String content) {

		 List<Post> postList =  postRepository.findByContentContainingIgnoreCase(content);
		 List<PostDto> postDtoList = postList.stream().map((post) -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		    return postDtoList;
	}

}
