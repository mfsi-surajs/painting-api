package com.api.painting.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.painting.model.GeneralResponse;
import com.api.painting.model.LoginResponse;
import com.api.painting.model.User;
import com.api.painting.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService/*,UserDetailsService*/ {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* (non-Javadoc)
	 * @see com.api.painting.service.UserService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public LoginResponse userLogin(String username, String password) {
		
		User user = userRepository.findByUsernameOrEmail(username,username);
		if(user == null) {
			return new LoginResponse(null, "0", "No user exists with this username.");
		}
		/*
		 * If user exists compare User entered password @password with user stored password @user.getPassword()
		 * Using method @passwordEncoder.matches(String plainPassword,String hashedPassword)
		 * On success/failure return LoginResponse object with corresponding success status(1/0) and message.
		 */
		else if (passwordEncoder.matches(password,user.getPassword())) {
			user.setPassword("");
			
			return new LoginResponse(user, "1", "Login success");
			
		}
		
		return new LoginResponse(null, "0", "Username and password did not match.");
		
	}

	@Override
	public GeneralResponse addNewUser(User user) {
		/*
		 * Adds new user details to User table
		 */
		userRepository.saveAndFlush(user);
		return new GeneralResponse("1", "Sign up complete.");
	}

	@Override
	public boolean userExists(String ueKey) {
		/*
		 * Returns True -> If User with requested @ueKey ( username or email ) exists 
		 * Returns False -> If User with requested @ueKey ( username or email ) does not exists 
		 */
		User user = userRepository.findByUsernameOrEmail(ueKey,ueKey);
		if(user == null) {
			return false;
		}
		return true;
	}

	@Override
	public Iterable<User> findAllUser() {
		/*
		 * Return all the users present in table.
		 */
		return userRepository.findAll();
	}

	@Override
	public boolean setUserProfileImagePath(String profileImagePath,Integer id) {
		
		User user = userRepository.findById(id);
		if(user == null) {
			return false;
		}
		
		user.setProfileImagePath(profileImagePath);
		
		userRepository.saveAndFlush(user);
		
		return true;
		
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameOrEmail(username,username);
	}
	
	@Override
	public User getUserById(Integer uid) {
		// TODO Auto-generated method stub
		return userRepository.findById(uid);
	}

	@Override
	public User editUser(User user) {
		user.setProfileImagePath(userRepository.findById(user.getId()).getProfileImagePath());
		userRepository.saveAndFlush(user);
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
	}*/
	
	

}
