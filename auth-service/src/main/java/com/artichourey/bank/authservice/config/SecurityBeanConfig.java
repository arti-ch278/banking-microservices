package com.artichourey.bank.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.artichourey.bank.authservice.audit.service.CustomUserDetailService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityBeanConfig {
	private final CustomUserDetailService userDetailsService;
	@Bean
	 PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    AuthenticationManager authenticationManager(
    		HttpSecurity http,
    		PasswordEncoder passwordEncoder) throws Exception{
    	AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	builder
    	.userDetailsService(userDetailsService)
    	.passwordEncoder(passwordEncoder);
    	return builder.build();
    }
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
    	
    	http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
    return http.build();
    }
    
    
	
	
}
