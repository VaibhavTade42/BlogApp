package com.blog.repository;

import java.util.List;

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
	public List<Post> findByUser(User user);
	
	//2. To find all post by category
	public List<Post> findByCategory(Category category);

	//search post using title
//	@Query("SELECT p FROM Post p WHERLE p.title LIKE %:title%")
//    List<Post> findTitleContainingKeywords(@Param("title") String title);
	
	
	public List<Post> findByPostTitleContainingIgnoreCase(String title);
	

}
