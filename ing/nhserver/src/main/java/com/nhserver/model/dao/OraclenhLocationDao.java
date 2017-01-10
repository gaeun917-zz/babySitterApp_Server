package com.nhserver.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Location;
import com.nhserver.model.mapper.nhLocationMapper;

@Repository(value = "oraclenhLocationDao")
public class OraclenhLocationDao implements nhLocationDao {
	
//	@Autowired
//	@Qualifier("sqlSessionTemplate")
//	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("nhLocationMapper")
	private nhLocationMapper locationMapper;
	
	@Override
	public void insert(Location location) {
		// TODO Auto-generated method stub
		locationMapper.insertLocation(location);
		
	}	

}









