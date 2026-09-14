package com.artichourey.bank.authservice.audit.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artichourey.bank.authservice.domain.User;
import com.artichourey.bank.authservice.dto.RegisterRequest;
import com.artichourey.bank.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public User register(RegisterRequest request) {
		User user =User.builder().userName(request.getUsername()).branchId(request.getBranchId()).employeeCode(request.getEmployeeCode())
				.password(passwordEncoder.encode(request.getPassword())).enabled(true).build();
		return userRepository.save(user);
		
	}

}
	