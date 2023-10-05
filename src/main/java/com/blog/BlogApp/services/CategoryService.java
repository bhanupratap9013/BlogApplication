package com.blog.BlogApp.services;

import java.util.List;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.Dto.UserDto;


public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	CategoryDto getCategoryById(int categoryId);
	List<CategoryDto> getAllCatgories();
	void deleteCategory(int categoryId);
}
