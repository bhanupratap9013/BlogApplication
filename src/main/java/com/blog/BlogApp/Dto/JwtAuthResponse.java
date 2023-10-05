package com.blog.BlogApp.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthResponse {

	private String jwtToken;
	
	private String username;
}