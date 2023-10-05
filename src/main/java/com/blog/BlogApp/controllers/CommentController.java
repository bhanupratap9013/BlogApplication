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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.Dto.CommentDto;
import com.blog.BlogApp.services.CategoryService;
import com.blog.BlogApp.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {
	 
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{postId}/comment")
	public ResponseEntity<CommentDto> addComment(@PathVariable Integer postId,
            @RequestBody CommentDto commentDto
			) {
		CommentDto savedDto = commentService.createComment(postId, commentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
	}
	
	@GetMapping("/{postId}/comment/{commentId}")
	public ResponseEntity delete(@PathVariable Integer postId,
			@PathVariable Integer commentId
			) {
		commentService.deleteComment(commentId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Comment Deleted");
	}
}
