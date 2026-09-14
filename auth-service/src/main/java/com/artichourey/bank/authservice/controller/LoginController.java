package com.artichourey.bank.authservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artichourey.bank.authservice.audit.service.AuditService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {
	
	private final AuditService auditService;
	
	@PostMapping("/login-success")
	public void loginSuccess(@RequestParam String username, HttpServletRequest request) {
		auditService.logSuccess(username, request.getRemoteAddr());
	}
	@PostMapping("/login-failure")
	public void loginFailure(@RequestParam String username, @RequestParam String reason, HttpServletRequest request) {
		auditService.logFailure(username, username, request.getRemoteAddr());
	}
	

}
