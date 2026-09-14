package com.artichourey.bank.authservice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.artichourey.bank.authservice.audit.service.UserService;
import com.artichourey.bank.authservice.domain.User;
import com.artichourey.bank.authservice.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/register")
	public User Register (@RequestBody RegisterRequest request) {
		
		
		return userService.register(request);
		
	}
	
}
