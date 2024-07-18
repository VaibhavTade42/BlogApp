package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.payload.PostDto;
import com.blog.service.PostService;


@RestController
@RequestMapping("/post/")

public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			                                  @PathVariable Long userId,
			                                  @PathVariable Long categoryId)
	
	{
		PostDto savedPost = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(savedPost, HttpStatus.CREATED);
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
	
	// Get all Posts
    @GetMapping("/getall/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> listPostDto = postService.getAllPost();
        return new ResponseEntity<>(listPostDto, HttpStatus.OK);
    }
}
