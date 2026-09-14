package com.artichourey.bank.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artichourey.bank.authservice.audit.LoginAuditEntity;

public interface LoginAuditRespository extends JpaRepository<LoginAuditEntity, Long> {

}
