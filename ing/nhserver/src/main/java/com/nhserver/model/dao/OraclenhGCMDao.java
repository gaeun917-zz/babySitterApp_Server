package com.nhserver.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Board;
import com.nhserver.model.dto.BoardComment;
import com.nhserver.model.dto.GCM;
import com.nhserver.model.mapper.BoardMapper;
import com.nhserver.model.mapper.nhGcmMapper;

@Repository("oraclenhGCMDao")
public class OraclenhGCMDao implements nhGCMDao {

	@Autowired
	@Qualifier("nhGcmMapper")
	private nhGcmMapper nhGcmMapper;

	@Override
	public void insertGCM(GCM gcm) {
		// TODO Auto-generated method stub
		nhGcmMapper.insertGCM(gcm);
	}

	@Override
	public List<GCM> selectGCMs() {
		// TODO Auto-generated method stub
		return nhGcmMapper.selectGCMs();
	}

	@Override
	public List<GCM> selectGCMBypId(String pId) {
		// TODO Auto-generated method stub
		return nhGcmMapper.selectGCMBypId(pId);
	}

	@Override
	public List<GCM> selectGCMBycId(String cId) {
		// TODO Auto-generated method stub
		return nhGcmMapper.selectGCMBycId(cId);
	}

	@Override
	public void updateTokenByPid(GCM gcm) {
		nhGcmMapper.updateTokenByPid(gcm);
		
	}

	@Override
	public void updateTokenByCid(GCM gcm) {
		// TODO Auto-generated method stub
		nhGcmMapper.updateTokenByCid(gcm);
	}
	

	

	
}