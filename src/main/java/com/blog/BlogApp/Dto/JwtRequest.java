package com.blog.BlogApp.Dto;

import lombok.Data;

@Data
public class JwtRequest {
	private String email;
	private String password;
}
