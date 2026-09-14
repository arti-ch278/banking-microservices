package com.artichourey.bank.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artichourey.bank.authservice.domain.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
	
	Optional<Client> findByClientId(String clientId);

}
