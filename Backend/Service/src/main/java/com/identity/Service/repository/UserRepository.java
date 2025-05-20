package com.identity.Service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.identity.Service.entity.User;
import com.identity.Service.enums.UserRole;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findFirstByEmail(String email);
//	UserDetails findFirstByEmail(String email);

	User findByRole(UserRole userRole);

}
