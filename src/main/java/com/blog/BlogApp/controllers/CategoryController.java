package com.blog.BlogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	 
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public ResponseEntity<CategoryDto> addcategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDtoCreated = categoryService.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDtoCreated);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getcategory(@PathVariable String categoryId) {
		CategoryDto categoryDto = categoryService.getCategoryById(Integer.parseInt(categoryId));
		return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getcategorys() {
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCatgories());
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatecategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
		CategoryDto categoryDtoUpdated = categoryService.updateCategory(categoryDto, Integer.parseInt(categoryId));
		return ResponseEntity.status(HttpStatus.OK).body(categoryDtoUpdated);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity deletecategory(@PathVariable String categoryId) {
		categoryService.deleteCategory(Integer.parseInt(categoryId));
		return ResponseEntity.status(HttpStatus.OK).body("category Deleted Successfully"); 
	}
	
}
