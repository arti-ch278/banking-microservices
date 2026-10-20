package com.artichourey.bank.authservice.security;

import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

public class OtpAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

	private final String username;
	private final String otp;
	public OtpAuthenticationToken(Authentication clientPrincipal ,Set<String> scopes,
			Map<String, Object> additionalParameter) {
		super(new AuthorizationGrantType("otp"), clientPrincipal, additionalParameter);
		this.username=(String)additionalParameter.get("username");
		this.otp=(String)additionalParameter.get("otp");	
		
	}
	public String getUsername() {
		return username;
		
	}
	public String getOtp() {
		return otp;
	}
	

}
