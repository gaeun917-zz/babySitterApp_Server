package com.babySitter.model.service;

import java.util.List;

import com.babySitter.model.dto.Mother;

public interface MotherService {

	
	// List<Mother> : mother 1,2,3,4,5 이렇게 있고, 
		// Mother는 mother6의 모든 데이터
	void registerMother(Mother mother);
	Mother login(String email, String password);
	List<Mother> getAllMother();
	Mother searchMotherByEmail(String email);
	Mother getMotherByMotherNo(int mother_no);
	

}
