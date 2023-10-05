package com.blog.BlogApp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.BlogApp.Dto.CategoryDto;
import com.blog.BlogApp.Dto.PostDto;
import com.blog.BlogApp.Dto.PostResponse;
import com.blog.BlogApp.Dto.UserDto;
import com.blog.BlogApp.config.ConfigurationBeans;
import com.blog.BlogApp.entities.Category;
import com.blog.BlogApp.entities.Post;
import com.blog.BlogApp.entities.User;
import com.blog.BlogApp.repositories.CategoryRepo;
import com.blog.BlogApp.repositories.PostRepo;
import com.blog.BlogApp.repositories.UserRepo;
import com.blog.BlogApp.exceptions.ResourceNotFoundException;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserService userService;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = modelMapper.map(userService.getUserById(userId), User.class);
		Category category = modelMapper.map(categoryService.getCategoryById(categoryId), Category.class);

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post savedPost = postRepo.save(post);

		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		PostDto postDtoById = getPostById(postId);
		
		postDtoById.setContent(postDto.getContent());
		postDtoById.setContent(postDto.getImageName());
		postDtoById.setContent(postDto.getTitle());
		
		Post post = dtoToPost(postDtoById);
		Post updatedPost = postRepo.save(post);
		
		return postToDto(updatedPost);
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		return this.postToDto(post);
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String orderBy) {
		
		Sort sort = orderBy.equals("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
		Page<Post> page = postRepo.findAll(pageable); 
		
		PostResponse postResponse = new PostResponse();
		postResponse.setPostDtos(page.getContent().stream().map(this::postToDto).collect(Collectors.toList()));
		postResponse.setLastPage(page.isLast());
		postResponse.setPageNumber(page.getNumber());
		postResponse.setTotalPosts((int)page.getTotalElements());
		postResponse.setPageSize(page.getSize());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(int pageNumber, int pageSize, int userId) {
		UserDto userDto = userService.getUserById(userId);
		User user = dtoToUser(userDto);
		List<Post> posts =  postRepo.findByUser(user);
		
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		Page<Post> page = postRepo.findAll(pageable); 
		
		PostResponse postResponse = new PostResponse();
		postResponse.setPostDtos(page.getContent().stream().map(this::postToDto).collect(Collectors.toList()));
		postResponse.setLastPage(page.isLast());
		postResponse.setPageNumber(page.getNumber());
		postResponse.setTotalPosts((int)page.getTotalElements());
		postResponse.setPageSize(page.getSize());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(int pageNumber, int pageSize, int categoryId) {
		UserDto userDto = userService.getUserById(categoryId);
		User user = dtoToUser(userDto);
		List<Post> posts =  postRepo.findByUser(user);
		
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		Page<Post> page = postRepo.findAll(pageable); 
		
		PostResponse postResponse = new PostResponse();
		postResponse.setPostDtos(page.getContent().stream().map(this::postToDto).collect(Collectors.toList()));
		postResponse.setLastPage(page.isLast());
		postResponse.setPageNumber(page.getNumber());
		postResponse.setTotalPosts((int)page.getTotalElements());
		postResponse.setPageSize(page.getSize());
		
		return postResponse;
	}

	@Transactional
	@Override
	public void deletePost(int postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		//System.out.println(post.getContent());
		postRepo.deleteById(postId);
	}
	
	public Post dtoToPost(PostDto postDto) {
		return modelMapper.map(postDto,Post.class);
	} 
	
	public PostDto postToDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

	
	public User dtoToUser(UserDto userDto) {
		return modelMapper.map(userDto,User.class);
	} 
	
	public UserDto userToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	} 
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto,Category.class);
	} 
	
	public CategoryDto categoryToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<PostDto> searchPostsByTitle(String title) {
		List<Post> listPosts = postRepo.findByTitleContaining(title);
		return listPosts.stream().map((post)-> postToDto(post)).collect(Collectors.toList());
	} 
}
