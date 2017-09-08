package com.api.painting.controller;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.api.painting.model.Countries;
import com.api.painting.model.GeneralResponse;
import com.api.painting.model.LoginBody;
import com.api.painting.model.LoginResponse;
import com.api.painting.model.PaintingListResponse;
import com.api.painting.model.User;
import com.api.painting.model.UserImages;
import com.api.painting.services.CountriesService;
import com.api.painting.services.UserPaintingService;
import com.api.painting.services.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/paintingApis")
public class MainController {

	/*
	 * @UPLOADED_FOLDER holds the location of file uploads.
	 */
	private static final String UPLOADED_FOLDER = "/home/mindfire/Documents/workspace-sts-3.9.0.RELEASE/painting-api/userImages/profileImages/";
	private static final Integer IMAGE_PUBLIC_STATUS = 1; // 0 -> private 1-> public
	private static final Integer IMAGE_PRIVATE_STATUS = 0; // 0 -> private 1-> public

	@Autowired
	private UserService userService;

	@Autowired
	private UserPaintingService userPaintingService;
	
	@Autowired 
	private CountriesService countriesService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * Creates/Registers a new user.
	 */
	@PostMapping(path = "/addUser")
	public @ResponseBody ResponseEntity<?> addNewUser(@RequestBody User user) {

		if (userService.userExists(user.getUsername()))
			return new ResponseEntity<Object>(new GeneralResponse("0", "User Exists"), HttpStatus.OK);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.addNewUser(user);
		return new ResponseEntity<Object>(new GeneralResponse("1", "User Added"), new HttpHeaders(), HttpStatus.OK);

	}
	
	
	/*
	 * Updates details of a user.
	 */
	@PostMapping(path = "/editUser")
	public @ResponseBody ResponseEntity<?> editUser(@RequestBody User user) {

		/*if (!userService.userExists(user.getUsername())) 
			return new ResponseEntity<Object>(new GeneralResponse("0", "User does not Exist."), HttpStatus.BAD_REQUEST);*/
		/*if (userService.userExists(user.getUsername()))
			return new ResponseEntity<Object>(new GeneralResponse("0", "User Exists"), HttpStatus.OK);*/
		
		if(user.getPassword().equals(null) || user.getPassword().equals("") ) {
			/* to avoiding null replacement while updating user record
			 * Set the user password before updating to table.
			 */
			user.setPassword(userService.getUserById(user.getId()).getPassword()); 
			
			/* to avoiding null replacement while updating user record
			 * Set the user password before updating to table.
			 */
			user.setProfileImagePath(userService.getUserById(user.getId()).getProfileImagePath()); 
		}
		else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userService.addNewUser(user);
		return new ResponseEntity<Object>(new GeneralResponse("1", "User Added"), new HttpHeaders(), HttpStatus.OK);

	}
	
	
	@PostMapping(path = "/addCountry")
	public @ResponseBody ResponseEntity<?> addCountry(@RequestBody Countries countries) {
		return new ResponseEntity<Object>(new GeneralResponse("1", "User Added"), new HttpHeaders(), HttpStatus.OK);

	}
	
	@GetMapping(path = "/getCountries")
	public @ResponseBody ResponseEntity<?> getCountries() {
		return new ResponseEntity<Object>(countriesService.getAllCountries(), new HttpHeaders(), HttpStatus.OK);

	}

	/*
	 * Adds Profile image. Accepts MultiPartFile and username to save the file into
	 * the location @UPLOADED_FOLDER then saves generated file name to table.
	 */
	@PostMapping(path = "/addUserProfileImage") // Map ONLY Post Requests
	public @ResponseBody ResponseEntity<?> addUserProfileImage(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam Integer username) {
		// User user = new User();
		// user.setUsername(user1);
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<Object>(new GeneralResponse("0", "please select a file!"), HttpStatus.OK);
		}

		try {

			return saveProfileImage(uploadfile, username);

		} catch (IOException e) {
			return new ResponseEntity<Object>(
					new GeneralResponse("0", "Upload unsuccessful please try again: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/* save user profile image
	 * Takes user Multipart file and username and saves to folder @UPLOADED_FOLDER with @generatedFileName.
	 */
	private ResponseEntity<?> saveProfileImage(MultipartFile file, Integer username) throws IOException {

		if (file.isEmpty()) {
			return new ResponseEntity<Object>(new GeneralResponse("0", "Upload unsuccessful please try again"),
					HttpStatus.BAD_REQUEST);
		}
		//if (userService.getUser(username) == null)
			//return new ResponseEntity<Object>(new GeneralResponse("0", "User not exists"), HttpStatus.BAD_REQUEST);

		//Integer uid = userService.getUser(username).getId();
		Integer uid = username;
		String generatedFileName = "profile_image_" + uid + "." + file.getOriginalFilename().split("\\.")[1];
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + generatedFileName);
		System.out.println("path: " + path);
		Files.write(path, bytes);
		// return path.toString();
		if (!userService.setUserProfileImagePath(generatedFileName, username))
			return new ResponseEntity<Object>(new GeneralResponse("0", "Upload unsuccessful please try again"),
					HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(
				new GeneralResponse("1", "Successfully uploaded - " + file.getOriginalFilename()), new HttpHeaders(),
				HttpStatus.OK);

	}

	@PostMapping(path = "/addUserPaintingImage") // Map ONLY Post Requests
	public @ResponseBody ResponseEntity<?> addUserPaintingImage(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam String username) {
		
		if (uploadfile.isEmpty()) {
			return new ResponseEntity<Object>(new GeneralResponse("0", "please select a file!"), HttpStatus.OK);
		}

		try {

			return saveUserPainting(uploadfile, username);

		} catch (IOException e) {
			return new ResponseEntity<Object>(
					new GeneralResponse("0", "Upload unsuccessful please try again: " + e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	// save user painting
	private ResponseEntity<?> saveUserPainting(MultipartFile file, String username) throws IOException {

		if (file.isEmpty()) {
			return new ResponseEntity<Object>(new GeneralResponse("0", "Upload unsuccessful please try again"),
					HttpStatus.BAD_REQUEST);
		}
		if (userService.getUser(username) == null)
			return new ResponseEntity<Object>(new GeneralResponse("0", "User do not exists"), HttpStatus.BAD_REQUEST);

		Integer uid = userService.getUser(username).getId();
		Integer lastInsertedRecordId = userPaintingService.getLastInsertedRecordId();
		String generatedFileName = "painting_image_" + (lastInsertedRecordId + 1) + "."
				+ file.getOriginalFilename().split("\\.")[1];
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + uid + "/" + generatedFileName);
		Path pathDirectory = Paths.get(UPLOADED_FOLDER + uid);
		Files.createDirectories(pathDirectory);
		System.out.println("path: " + path);
		Files.write(path, bytes);
		UserImages userImages = new UserImages();
		userImages.setPaintingName(generatedFileName);
		userImages.setUid(uid);
		userImages.setPublicStatus(IMAGE_PRIVATE_STATUS);
		// return path.toString();
		if (!userPaintingService.addNewPainting(userImages))
			return new ResponseEntity<Object>(new GeneralResponse("0", "Upload unsuccessful please try again"),
					HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(
				new GeneralResponse("1", "Successfully uploaded - " + file.getOriginalFilename()), new HttpHeaders(),
				HttpStatus.OK);

	}

	/*
	 * Accepts Login body which holds username and password to authenticate.
	 */
	@PostMapping(path = "/login") // Map ONLY Post Requests
	public @ResponseBody ResponseEntity<?> userLogin(@RequestBody LoginBody loginBody) {
		LoginResponse loginResponse;
		try {
			loginResponse = userService.userLogin(loginBody.getName(), loginBody.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Object>(new GeneralResponse("0", "Login Failed."), HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<Object>(loginResponse, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.findAllUser();
	}

	/*
	 * Returns all the paintings of requested user @user.
	 */
	@PostMapping(path = "/userPaintings")
	public @ResponseBody List<UserImages> getUserPaintings(@RequestBody User user) {
		// This returns a JSON or XML with the users
		return userPaintingService.getUsersImages(user.getId());
	}

	/*
	 * private void saveUploadedFiles(List<MultipartFile> files) throws IOException
	 * {
	 * 
	 * for (MultipartFile file : files) {
	 * 
	 * if (file.isEmpty()) { continue; //next pls }
	 * 
	 * byte[] bytes = file.getBytes(); Path path = Paths.get(UPLOADED_FOLDER +
	 * file.getOriginalFilename()); System.out.println("path: "+path);
	 * Files.write(path, bytes);
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * Accepts user profile image name and returns the corresponding File bytes.
	 */
	@RequestMapping("/getProfileImage")
	public ResponseEntity<byte[]> getProfileImage(@RequestParam String path) throws IOException {

		try {
			File file = new File(UPLOADED_FOLDER + path);
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);

			return new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<byte[]>(Files.readAllBytes(new File(UPLOADED_FOLDER + "default-profile.png").toPath()), new HttpHeaders(), HttpStatus.CREATED);
		}
	}
	
	/*
	 * Accepts user ID and image painting name and returns the corresponding File bytes.
	 */
	@RequestMapping("/getPaintings")
	public ResponseEntity<byte[]> getPaintings(@RequestParam String path,@RequestParam String uid) throws IOException {

		try {
			File file = new File(UPLOADED_FOLDER+uid+"/" + path);
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);

			return new ResponseEntity<byte[]>(Files.readAllBytes(file.toPath()), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<byte[]>(Files.readAllBytes(new File(UPLOADED_FOLDER + "default-profile.png").toPath()), new HttpHeaders(), HttpStatus.CREATED);
		}
	}

	/*
	 * Returns user details to be shown on profile page.
	 */
	@PostMapping("/getUserPublicData")
	public @ResponseBody ResponseEntity<?> getUserPublicData(@RequestBody User uid) {

		User user = userService.getUserById(uid.getId());
		user.setPassword("");

		return new ResponseEntity<Object>(user, new HttpHeaders(), HttpStatus.OK);
	}

	/*
	 * Accepts user data to update existing data.
	 */
	@PostMapping("/editUserData")
	public @ResponseBody ResponseEntity<?> editUserData(@RequestBody User user) {
		if (!userService.userExists(user.getUsername()))
			return new ResponseEntity<Object>(new GeneralResponse("0", "User does not Exist"), HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Object>(user, new HttpHeaders(), HttpStatus.OK);
	}

	/*
	 * Returns List of user public paintings to be displayed on profile page.
	 */
	@PostMapping("/getUserPublicImages")
	public @ResponseBody ResponseEntity<?> getUserPublicImages(@RequestBody User uid) {

		List<UserImages> userImages = userPaintingService.getPublicImages(uid.getId(), IMAGE_PUBLIC_STATUS);

		return new ResponseEntity<Object>(new PaintingListResponse(userImages, "1", "Public painting list"),
				new HttpHeaders(), HttpStatus.OK);
	}
	
	/*
	 * Returns all the paintings of user.
	 */
	@PostMapping("/getUserAllImages")
	public @ResponseBody ResponseEntity<?> getUserAllImages(@RequestBody User uid) {

		List<UserImages> userImages = userPaintingService.getUserAllImages(uid.getId());

		return new ResponseEntity<Object>(new PaintingListResponse(userImages, "1", "Public painting list"),
				new HttpHeaders(), HttpStatus.OK);
	}

	/*
	 * Accepts UserImage and changes the corresponding painting's public status.
	 */
	@PostMapping("/changeImagePublicStatus")
	public @ResponseBody ResponseEntity<?> changeImagePublicStatus(@RequestBody UserImages userImage) {

		System.out.println("--->userImage: " + userImage.getPublicStatus());
		GeneralResponse response = userPaintingService.setImagePublicStatus(userImage.getId(),
				userImage.getPublicStatus());

		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
	}

}
