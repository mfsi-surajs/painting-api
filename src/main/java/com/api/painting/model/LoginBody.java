package com.api.painting.model;

public class LoginBody {
	
	private String name;
	private String password;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginBody(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public LoginBody() {
		
	}
	
	

}
