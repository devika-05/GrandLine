package com.identity.Service.dto;

import com.identity.Service.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
	
	private String jwt;
	private UserRole role;
	private String userId;
}
