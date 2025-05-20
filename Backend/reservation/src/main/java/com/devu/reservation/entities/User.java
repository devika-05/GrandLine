package com.devu.reservation.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devu.reservation.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Document(collection="User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
    private String id;
    private String userId;
    private String username;
    private String email;
    private String password;
    private UserRole role;
    
    private List<Reservation> reservations = new ArrayList<>();
}
