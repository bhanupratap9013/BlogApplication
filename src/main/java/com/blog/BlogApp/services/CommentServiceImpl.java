package com.blog.BlogApp.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.Dto.CommentDto;
import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.entities.Comment;
import com.blog.BlogApp.entities.Post;
import com.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.blog.BlogApp.repositories.CommentRepo;


@Service
 public class CommentServiceImpl implements CommentService {

	 @Autowired
	 private ModelMapper modelMapper;
	 
	 
	 @Autowired
	 private CommentRepo commentRepo;
	 
	 @Autowired
	 private PostService postService;

	@Override
	public CommentDto createComment(int postId, CommentDto commentDto) {
		Post post = modelMapper.map(postService.getPostById(postId), Post.class);
		
		Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        
        commentRepo.save(comment);
        return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		commentRepo.delete(comment);
	}
	 
	
	
	
}
