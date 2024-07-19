package com.blog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.PostService;


@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
//	    public PostController(PostService postService) {
//	        this.postService = postService;
//	    }
		
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			                                  @PathVariable Long userId,
			                                  @PathVariable Long categoryId)
	
	{
		PostDto savedPost = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(savedPost, HttpStatus.CREATED);
	}
	
	//Update Post
	@PutMapping("/update/{postId}/posts")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatePost);
	}
	
	//Get Post by User id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
		List<PostDto> postListDto = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postListDto, HttpStatus.OK);
	}
	
	//Get Post by Category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId){
		
		List<PostDto> postListDto =	postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postListDto, HttpStatus.OK);
	}
	
	//Get post by Post Id
	@GetMapping("/{postId}/posts")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
		PostDto postDto = postService.getPostById(postId);
		return ResponseEntity.ok(postDto);
		
	}
	
	//Get all posts
	@GetMapping("/getall/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = "4", required = false) Integer pageSize
			) {
		
	     PostResponse postResponse = postService.getAllPost(pageNumber, pageSize);
	    return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	//Delete post by post id
	@DeleteMapping("/delete-postById/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Long postId) {
		postService.deletePostById(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted with id: "+postId, 
				true, new Date(), HttpStatus.OK), HttpStatus.OK );
	}
	
//	search post using keyword
	@GetMapping("/search/posts")
	public ResponseEntity<List<PostDto>> searchPost(@RequestParam("title") String title){
		List<PostDto> allPosts = postService.findByPostTitleContainingIgnoreCase(title);
		return new ResponseEntity<>(allPosts, HttpStatus.OK);
	}

	

}
