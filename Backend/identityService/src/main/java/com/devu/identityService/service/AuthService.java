package com.devu.identityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devu.identityService.entities.User;
import com.devu.identityService.repository.UserRepository;

@Service
public class AuthService {
	 @Autowired
	 private UserRepository repository;
	 
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @Autowired
	 private JWTService jwtService;
	 
	 public String saveUser(User user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
	     repository.save(user);
	     return "user added to the system";
	 }
	 
	 
	 public String generateToken(String username) {
	        return jwtService.generateToken(username);
	    }

	    public void validateToken(String token) {
	        jwtService.validateToken(token);
	    }
}
