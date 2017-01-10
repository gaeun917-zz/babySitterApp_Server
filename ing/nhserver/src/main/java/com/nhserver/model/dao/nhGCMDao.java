package com.nhserver.model.dao;

import java.util.List;

import com.nhserver.model.dto.GCM;

public interface nhGCMDao {

	void insertGCM(GCM gcm);
	List<GCM> selectGCMs();
	List<GCM> selectGCMBypId(String pId);
	List<GCM> selectGCMBycId(String cId);
	void updateTokenByPid(GCM gcm);
	void updateTokenByCid(GCM gcm);
}