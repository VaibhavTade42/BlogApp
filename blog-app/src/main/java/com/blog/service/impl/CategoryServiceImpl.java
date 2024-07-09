package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.payload.UserDto;
import com.blog.repository.CategoryRepository;
import com.blog.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category savedCategory = this.categoryRepository.save(mapper.map(categoryDto, Category.class));
		
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) {
	Category category =	this.categoryRepository.findById(categoryId)
			.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
	
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		return this.categoryRepository.findAll()
				.stream() //Stream<Category>
				.map(category -> this.mapper.map(category,CategoryDto.class)) //Stream <DTO>
				.collect(Collectors.toList());//List<DTO>	
	}

	@Override
	public void deleteCategoryById(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		
		this.categoryRepository.deleteById(categoryId);
		
	}

}
