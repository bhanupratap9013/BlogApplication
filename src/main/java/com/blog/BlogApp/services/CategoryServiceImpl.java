package com.blog.BlogApp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.config.ConfigurationBeans;
import com.blog.BlogApp.entities.Category;
import com.blog.BlogApp.repositories.CategoryRepo;
import com.blog.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = categoryRepo.save(category);
		return this.categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = categoryRepo.findById(categoryId)
			    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = categoryRepo.save(category);
		CategoryDto categoryDtoReturn = this.categoryToDto(updatedCategory);
		return categoryDtoReturn;
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = categoryRepo.findById(categoryId)
			    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		CategoryDto categoryDtoReturn = this.categoryToDto(category);
		return categoryDtoReturn;
	}

	@Override
	public List<CategoryDto> getAllCatgories() {
		
		List<Category> categoryList = categoryRepo.findAll();
		
		return categoryList.stream()
                .map(this::categoryToDto)
                .collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category category =  categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		categoryRepo.delete(category);
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto,Category.class);
	} 
	
	public CategoryDto categoryToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	} 

}




