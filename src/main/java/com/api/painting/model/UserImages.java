package com.api.painting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserImages {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Integer uid;
	private String paintingName;
	private Integer publicStatus;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the paintingName
	 */
	public String getPaintingName() {
		return paintingName;
	}
	/**
	 * @param paintingName the paintingName to set
	 */
	public void setPaintingName(String paintingName) {
		this.paintingName = paintingName;
	}
	/**
	 * @return the publicStatus
	 */
	public Integer getPublicStatus() {
		return publicStatus;
	}
	/**
	 * @param publicStatus the publicStatus to set
	 */
	public void setPublicStatus(Integer publicStatus) {
		this.publicStatus = publicStatus;
	}
	/**
	 * @return the user
	 */
	
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
