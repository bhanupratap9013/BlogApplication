package com.blog.BlogApp.services;

import java.util.List;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.Dto.CommentDto;
import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.entities.Comment;


public interface CommentService {
	CommentDto createComment(int postId,CommentDto commentDto);
	public void deleteComment(int commentId);
}
