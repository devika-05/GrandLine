package com.devu.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.devu.reservation.entities.User;
import com.devu.reservation.enums.UserRole;
import com.devu.reservation.repository.UserRepository;
@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Transactional
    public User createUser(User user) {
    	user.setRole(UserRole.CUSTOMER);
        return userRepository.save(user);
    }
    
    @Transactional
	public User updateUser(String userId, User updatedUser) {
		 // Find the user by ID
        User existingUser = userRepository.findByUserId(userId);

        // Check if the user exists
        if (existingUser != null) {
            // Update the fields of the existing user with the new values
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

            // Save the updated user
            return userRepository.save(existingUser);
        } else {
            // If the user does not exist, throw an exception or handle the situation accordingly
            throw new RuntimeException("User not found with ID: " + userId);
        }
	}

    @Transactional
    public void deleteUser(String userId) {
        // Find the user by ID
        User user = userRepository.findByUserId(userId);
        
        // Check if the user exists
        if (user != null) {
            // If the user exists, delete the user
            userRepository.delete(user);
        } else {
            // If the user does not exist, throw an exception or handle the situation accordingly
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

	public User getUser(String userId) {
		// Find the user by ID
        User viewUser = userRepository.findByUserId(userId);

        // Check if the user exists
        if (viewUser != null) {
            return viewUser;
        } else {
            // If the user does not exist, you can choose to throw an exception or return null
            throw new RuntimeException("User not found with ID: " + userId);
        }
	}

}
