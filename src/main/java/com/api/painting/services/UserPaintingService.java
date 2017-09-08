package com.api.painting.services;

import java.util.List;

import com.api.painting.model.GeneralResponse;
import com.api.painting.model.UserImages;

public interface UserPaintingService {

	List<UserImages> getUsersImages(Integer uid);
	
	UserImages getLastInsertedRecord();
	
	boolean addNewPainting(UserImages userImages);

	Integer getLastInsertedRecordId();
	
	List<UserImages> getPublicImages(Integer uid,Integer status);

	GeneralResponse setImagePublicStatus(Integer id, Integer publicStatus);
	
	List<UserImages> getUserAllImages(Integer id);

}