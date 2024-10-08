package com.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.payload.PostDto;


public interface PostRepository extends JpaRepository<Post, Long>{
	
	//custom finder methods in JPA
	//1. To find all post of a user 
	Page<Post> findByUser(User user, Pageable pageable);
	
	//2. To find all post by category
	public Page<Post> findByCategory(Category category, Pageable pageable);

	
//	@Query("SELECT p FROM Post p WHERLE p.title LIKE %:title%")
//    List<Post> findTitleContainingKeywords(@Param("title") String title);
	
	//search post using post title
	public List<Post> findByPostTitleContainingIgnoreCase(String title);

	//search post using post content
	public List<Post> findByContentContainingIgnoreCase(String content);
	
	
	

}
