package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Post;
import com.blog.payload.PostDto;
import com.blog.service.PostService;


@RestController
@RequestMapping("/post")

public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			                                  @PathVariable Long userId,
			                                  @PathVariable Long categoryId)
	
	{
		PostDto savedPost = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(savedPost, HttpStatus.CREATED);
	}
	
//	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto){
//		
//	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId){
		List<PostDto> postListDto = postService.getPostByUser(userId);
		return ResponseEntity.ok(postListDto); 
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long categoryId){
		
		List<PostDto> postListDto =	postService.getPostByCategory(categoryId);
		return ResponseEntity.ok(postListDto);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
		PostDto postDto = postService.getPostById(postId);
		return ResponseEntity.ok(postDto);
		
	}

}
