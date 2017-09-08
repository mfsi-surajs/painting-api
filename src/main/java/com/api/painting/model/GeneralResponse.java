package com.api.painting.model;

public class GeneralResponse {
	
	private String success;
	private String message;
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	public GeneralResponse(String success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	

}
