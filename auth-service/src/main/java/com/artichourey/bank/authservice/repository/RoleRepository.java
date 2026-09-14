package com.artichourey.bank.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artichourey.bank.authservice.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
