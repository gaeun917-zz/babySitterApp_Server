package com.nhserver.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.nhserver.model.dto.GCM;
import com.nhserver.model.dto.Member;

public interface nhGcmMapper {

	void insertGCM(GCM gcm);
	List<GCM> selectGCMs();
	List<GCM> selectGCMBypId(String pId);
	List<GCM> selectGCMBycId(String cId);
	void updateTokenByPid(GCM gcm);
	void updateTokenByCid(GCM gcm);
	
}



