package com.identity.Service.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class SignupRequest {
	
	private String id=UUID.randomUUID().toString();
	private String userId;
    private String userName;
    private String email;
    private String password;
}
