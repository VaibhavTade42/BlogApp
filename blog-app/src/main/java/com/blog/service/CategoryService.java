package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);

	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

	//getById
	public CategoryDto getCategoryById(Long categoryId);

	//getAll
	public List<CategoryDto> getAllCategory();

	//deleteById
	public void deleteCategoryById(Long categoryId);

}
