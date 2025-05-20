package com.devu.identityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devu.identityService.dto.AuthRequest;
import com.devu.identityService.entities.User;
import com.devu.identityService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<?> addNewUser(@RequestBody User user) {
	    String userId = service.saveUser(user);
	    if (userId != null) {
	        return ResponseEntity.ok().body("User successfully registered with id: " + userId);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
	    }
	}
	
	@PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
	
//	@PostMapping("/token")
//	public String getToken(@RequestBody AuthRequest authRequest) {
//		return service.generateToken(authRequest.getUsername());
//	}
	
	@GetMapping("/validate")
	public String validateToken(@RequestParam("token")String token) {
		service.validateToken(token);
		return "Token is valid";
	}
	

}
