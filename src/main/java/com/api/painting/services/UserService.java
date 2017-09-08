package com.api.painting.services;

import com.api.painting.model.GeneralResponse;
import com.api.painting.model.LoginResponse;
import com.api.painting.model.User;


public interface UserService {

	LoginResponse userLogin(String username, String password);
	
	GeneralResponse addNewUser(User user);
	
	boolean userExists(String ueKey);

	Iterable<User> findAllUser();

	boolean setUserProfileImagePath(String path, Integer id);
	
	User getUser(String username);

	User getUserById(Integer uid);
	
	User editUser(User user);

}