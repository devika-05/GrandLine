package com.identity.Service.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.identity.Service.dto.SignupRequest;
import com.identity.Service.dto.UserDto;
import com.identity.Service.entity.User;
import com.identity.Service.enums.UserRole;
import com.identity.Service.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
		
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createCustomer(SignupRequest signupRequest) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(signupRequest.getId());
		user.setUserId(signupRequest.getUserId());
		user.setUsername(signupRequest.getUserName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser=userRepository.save(user);
		UserDto userDto=new UserDto();
		userDto.setId(createdUser.getId());
		return userDto;
		
	}


	@Override
	public boolean hasCutomerWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount() {
		User adminAccount= userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount == null) {
			User newAdminAccount = new User();
			newAdminAccount.setUserId("Admin");
			newAdminAccount.setUsername("Admin");
			newAdminAccount.setEmail("admin@gmail.com");
			newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
			newAdminAccount.setRole(UserRole.ADMIN);
			userRepository.save(newAdminAccount);
			System.out.println("Admin Account Created sucessfully");
		}
	}
}
