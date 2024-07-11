package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	//custom finder methods in JPA
	//1. To find all post of a user 
	public List<Post> findByUser(User user);
	
	//2. To find all post by category
	public List<Category> findByUser(Category category);
	
	
	
	

}
