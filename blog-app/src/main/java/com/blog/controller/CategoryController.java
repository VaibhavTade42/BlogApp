package com.blog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create category
	@PostMapping("/save-category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto savedCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.OK);
		
	}
	
	@PutMapping("/update-category/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Long categoryId ){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	@GetMapping("/get-categoryById/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
	CategoryDto categoryDto =	this.categoryService.getCategoryById(categoryId);
	return ResponseEntity.ok(categoryDto);
	}
	
	@GetMapping("/getall")
		public ResponseEntity<List<CategoryDto>> getAllCatories(){
		List<CategoryDto> categoryDto  = this.categoryService.getAllCategory();
		return ResponseEntity.ok(categoryDto);
		}
	
	@DeleteMapping("/delete-categoryById/{id}")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Long categoryId){
		
		this.categoryService.deleteCategoryById(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category successfully deleted with id: "+categoryId, 
				true, new Date(), HttpStatus.OK), HttpStatus.OK );
	}
	
	
	
	
	
	
	
}




