package com.artichourey.bank.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

	private String username;
	private String password;
	private String branchId;
	private String employeeCode;
}
