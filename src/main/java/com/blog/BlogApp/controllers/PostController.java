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

import com.blog.BlogApp.Dto.PostDto;
import com.blog.BlogApp.Dto.PostResponse;
import com.blog.BlogApp.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> addpost(@Valid @RequestBody PostDto postDto, @PathVariable String userId,
			@PathVariable String categoryId) {
		PostDto postDtoCreated = postService.createPost(postDto, Integer.parseInt(userId),Integer.parseInt(categoryId));
		return ResponseEntity.status(HttpStatus.CREATED).body(postDtoCreated);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpost(@PathVariable String postId) {
		PostDto postDto = postService.getPostById(Integer.parseInt(postId));
		return ResponseEntity.status(HttpStatus.OK).body(postDto);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(@RequestParam(defaultValue = "0", required = false, name ="pageNumber") Integer pageNumber,
			@RequestParam(defaultValue = "4", required = false, name ="pageSize") Integer pageSize,
			@PathVariable String categoryId
			){
		PostResponse postResponse = postService.getPostsByUser(pageNumber, pageSize, Integer.parseInt(categoryId));
		return ResponseEntity.status(HttpStatus.OK).body(postResponse);
	} 
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@RequestParam(defaultValue = "0", required = false, name ="pageNumber") Integer pageNumber,
			@RequestParam(defaultValue = "4", required = false, name ="pageSize") Integer pageSize, @PathVariable String userId
			){
		PostResponse postResponse = postService.getPostsByUser(pageNumber, pageSize, Integer.parseInt(userId));
		return ResponseEntity.status(HttpStatus.OK).body(postResponse);
	} 

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getposts(@RequestParam(defaultValue = "0", required = false, name ="pageNumber") Integer pageNumber,
			@RequestParam(defaultValue = "4", required = false, name ="pageSize") Integer pageSize,
			@RequestParam(defaultValue = "postId", required = false, name ="sortBy") String sortBy,
			@RequestParam(defaultValue = "asc", required = false, name ="orderBy") String orderBy
			) {
		return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts(pageNumber, pageSize, sortBy, orderBy));
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatepost(@Valid @RequestBody PostDto postDto, @PathVariable String postId) {
		PostDto postDtoUpdated = postService.updatePost(postDto, Integer.parseInt(postId));
		return ResponseEntity.status(HttpStatus.OK).body(postDtoUpdated);
	}
	
	@GetMapping("/posts/search/{searchParam}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String searchParam) {
		List<PostDto> searchedPost = postService.searchPostsByTitle(searchParam);
		return ResponseEntity.status(HttpStatus.OK).body(searchedPost);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity deletepost(@PathVariable String postId) {
		postService.deletePost(Integer.parseInt(postId));
		return ResponseEntity.status(HttpStatus.OK).body("post Deleted Successfully");
	}

}
