package com.api.painting.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.painting.model.UserImages;
import java.lang.Integer;

@Repository
public interface UserPaintingRepository extends JpaRepository<UserImages, Long> {
	
	List<UserImages> findByUid(Integer uid);
	
	UserImages findTopByOrderByIdDesc();
	
	List<UserImages> findByUidAndPublicStatus(Integer uid,Integer status);

	UserImages findById(Integer id);
	
	//UserImages findFirstByOrderById();
	
	

	
}
