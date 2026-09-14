package com.artichourey.bank.authservice.audit.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.artichourey.bank.authservice.audit.LoginAuditEntity;
import com.artichourey.bank.authservice.repository.LoginAuditRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditService {
	private final LoginAuditRespository auditRepository;
	public void logSuccess(String username, String ip) {
		auditRepository.save(LoginAuditEntity.builder()
				.userName(username)
				.success(true)
				.ipAddress(ip)
				.loginTime(LocalDateTime.now())
				.build());
	}
	public void logFailure(String username,String ip, String reason) {
		auditRepository.save(LoginAuditEntity.builder()
				.userName(username)
				.success(true)
				.ipAddress(ip)
				.loginTime(LocalDateTime.now())
				.build());
		
	}

}
