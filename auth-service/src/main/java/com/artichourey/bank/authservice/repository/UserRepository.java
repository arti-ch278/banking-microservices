package com.artichourey.bank.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artichourey.bank.authservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByUserName(String username);
	
}
