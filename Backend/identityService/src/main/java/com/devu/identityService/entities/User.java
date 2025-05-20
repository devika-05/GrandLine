package com.devu.identityService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devu.identityService.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private  String id;
    private String userId;
    private String userName;
    private String email;
    private String password;
   // private UserRole role;
    
//    @Lob
//    @Column(columnDefinition="longblob")
    //private byte[] img;
    
}
