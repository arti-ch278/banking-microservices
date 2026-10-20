package com.artichourey.bank.authservice.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artichourey.bank.authservice.audit.service.AuditService;
import com.artichourey.bank.authservice.audit.service.CustomUserDetailService;
import com.artichourey.bank.authservice.audit.service.EmailService;
import com.artichourey.bank.authservice.audit.service.OtpService;
import com.artichourey.bank.authservice.audit.service.SmsService;
import com.artichourey.bank.authservice.audit.service.UserService;
import com.artichourey.bank.authservice.domain.User;
import com.artichourey.bank.authservice.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final CustomUserDetailService customUserDetailService;
	
	private final AuditService auditService;
	private final UserService userService;
	private final UserRepository userRepository; 
	private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final SmsService smsService;
    private final EmailService emailService;
    
	
	@PostMapping("/login-success")
	public void loginSuccess(@RequestParam String username, HttpServletRequest request) {
		auditService.logSuccess(username, request.getRemoteAddr());
	}
	@PostMapping("/login-failure")
	public void loginFailure(@RequestParam String username, @RequestParam String reason, HttpServletRequest request) {
		auditService.logFailure(username, username, request.getRemoteAddr());
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String userName, @RequestParam  String password, HttpServletRequest request ){
		User user = userService.findByUserName(userName);
	if(!user.isAccountNonLocked()) {
		if(user.getLockTime().plusMinutes(15).isBefore(LocalDateTime.now())) {
			user.setAccountNonLocked(true);
			user.setFailedAttempts(0);
			userRepository.save(user);
		}else {
			throw new RuntimeException("Account locked. try after 15 minutes");
		}
		if(passwordEncoder.matches(password, user.getPassword())) {
			user.setFailedAttempts(user.getFailedAttempts()+1);
			if(user.getFailedAttempts() >=5) {
				user.setAccountNonLocked(false);
				user.setLockTime(LocalDateTime.now());
			}
			userRepository.save(user);
			throw new RuntimeException("Invalid Credentials");
		}
		user.setFailedAttempts(0);
		userRepository.save(user);
		String ip = request.getRemoteAddr();
		String deviceId=request.getHeader("X-Device-Id");
		String otp=otpService.generateOtp(userName,ip,deviceId);
		smsService.sendOtp(user.getPhoneNumber(), otp);
		emailService.sendOtp(user.getEmail(), otp);
		
	}
	return ResponseEntity.ok("OTP Send");	
	}

}
