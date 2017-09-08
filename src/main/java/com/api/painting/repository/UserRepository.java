package com.api.painting.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.painting.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameOrEmail(String username,String email);

	User findByUsername(String username);
	
	User findById(Integer id);
	
}
