package com.api.painting.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.painting.model.GeneralResponse;
import com.api.painting.model.UserImages;
import com.api.painting.repository.UserPaintingRepository;

@Service
public class UserPaintingServiceImpl implements UserPaintingService {
	
	@Autowired
	private UserPaintingRepository userPaintingRepository;
	

	/* (non-Javadoc)
	 * @see com.api.painting.services.UserPaintingService#getUsersImages(java.lang.Integer)
	 */
	@Override
	public List<UserImages> getUsersImages(Integer uid){
		return userPaintingRepository.findByUid(uid);
	}


	@Override
	public UserImages getLastInsertedRecord() {
		return userPaintingRepository.findTopByOrderByIdDesc();
	}
	
	@Override
	public Integer getLastInsertedRecordId() {
		try {
			return userPaintingRepository.findTopByOrderByIdDesc().getId();
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public boolean addNewPainting(UserImages userImages) {
		// TODO Auto-generated method stub
		try {
			userPaintingRepository.save(userImages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<UserImages> getPublicImages(Integer uid,Integer status) {
		return userPaintingRepository.findByUidAndPublicStatus(uid, status);
		
	}


	@Override
	public GeneralResponse setImagePublicStatus(Integer id, Integer publicStatus) {
		
		try {
			UserImages userImages = userPaintingRepository.findById(id);
			userImages.setPublicStatus(publicStatus);
			userPaintingRepository.saveAndFlush(userImages);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new GeneralResponse("0", "Error occured");
		}
		
		return new GeneralResponse("1", "Painting status changed successfully");
		
	}


	@Override
	public List<UserImages> getUserAllImages(Integer id) {
		return userPaintingRepository.findByUid(id);
	}
	
	
	
	
	
	
}
