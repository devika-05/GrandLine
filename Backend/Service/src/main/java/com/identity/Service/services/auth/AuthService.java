package com.identity.Service.services.auth;

import com.identity.Service.dto.SignupRequest;
import com.identity.Service.dto.UserDto;

public interface AuthService {
	
	UserDto createCustomer(SignupRequest signupRequest);
	boolean hasCutomerWithEmail(String email);
}
