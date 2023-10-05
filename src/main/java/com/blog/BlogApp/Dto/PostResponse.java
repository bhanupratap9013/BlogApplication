package com.blog.BlogApp.Dto;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {
	private List<PostDto> postDtos;
	private int pageNumber;
	private int pageSize;
	private int totalPosts;
	private int totatlPages;
	private boolean isLastPage;
}
