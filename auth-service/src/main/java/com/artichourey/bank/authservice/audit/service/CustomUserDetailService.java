package com.artichourey.bank.authservice.audit.service;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artichourey.bank.authservice.domain.User;
import com.artichourey.bank.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.getRoles().stream()
				.map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
	}

}
