package com.blog.BlogApp.Dto;

import java.util.Date;
import java.util.List;

import com.blog.BlogApp.entities.Category;
import com.blog.BlogApp.entities.Comment;
import com.blog.BlogApp.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private Integer postId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comments;
}



