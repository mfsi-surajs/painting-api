package com.api.painting.model;

import java.util.List;

public class PaintingListResponse {
	
	private List<UserImages> userImages;
	private String success;
	private String message;
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
	public PaintingListResponse(List<UserImages> userImages, String success, String message) {
		super();
		this.userImages = userImages;
		this.success = success;
		this.message = message;
	}
	

}
