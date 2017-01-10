package com.nhserver.model.dao;

import com.nhserver.model.dto.Manbo;

public interface nhManboDao {

	void insert(Manbo manbo);

	Manbo getRegDateByMemberId(String memberId); 
	
//	List<Manbo> getList();
//
//	Member getMemberById(String id);
//
//	Member getMemberByIdAndPasswd(String id, String passwd);

}