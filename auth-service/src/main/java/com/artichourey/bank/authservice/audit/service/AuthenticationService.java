package com.artichourey.bank.authservice.audit.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.artichourey.bank.authservice.domain.User;
import com.artichourey.bank.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final UserRepository userRepository;
	public User authenticate(String username) {
	return userRepository.findByUserName(username).filter(User::isEnabled).orElseThrow(()-> new BadCredentialsException("invalid user or disabled user"));

}
}