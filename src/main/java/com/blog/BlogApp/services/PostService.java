package com.blog.BlogApp.services;

import java.util.List;

import com.blog.BlogApp.Dto.PostDto;
import com.blog.BlogApp.Dto.PostResponse;
import com.blog.BlogApp.entities.Post;


public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, int postId);
	PostDto getPostById(int postId);
	PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy, String orderBy);
	PostResponse getPostsByUser(int pageNumber, int pageSize,int userId);
	PostResponse getPostsByCategory(int pageNumber, int pageSize, int categoryId);
	void deletePost(int postId);
	List<PostDto> searchPostsByTitle(String title);
}
