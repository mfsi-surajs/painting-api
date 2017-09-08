package com.api.painting.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String profileImagePath;
	private String phone;
	private String city;
	private String country;
	@Column(unique = true)
	private String username;
	@Column(length = 2255)
	private String userDetails;
	private Integer LoginStatus;
	
	@OneToMany(targetEntity=UserImages.class,mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserImages> userImages;
	

	/**
	 * @return the userImages
	 */
	
	public List<UserImages> getUserImages() {
		return userImages;
	}

	/**
	 * @param userImages the userImages to set
	 */
	public void setUserImages(List<UserImages> userImages) {
		this.userImages = userImages;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the profileImagePath
	 */
	public String getProfileImagePath() {
		return profileImagePath;
	}

	/**
	 * @param profileImagePath
	 *            the profileImagePath to set
	 */
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userDetails
	 */
	public String getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails
	 *            the userDetails to set
	 */
	public void setUserDetails(String userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return the loginStatus
	 */
	public Integer getLoginStatus() {
		return LoginStatus;
	}

	/**
	 * @param loginStatus
	 *            the loginStatus to set
	 */
	public void setLoginStatus(Integer loginStatus) {
		LoginStatus = loginStatus;
	}

}
