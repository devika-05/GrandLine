package com.identity.Service.dto;

import com.identity.Service.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	
	private  String id;
    private String userId;
    private String userName;
    private String email;
    private UserRole role;
}
