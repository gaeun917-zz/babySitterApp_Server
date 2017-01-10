package com.babySitter.model.service;

import java.sql.Timestamp;
import java.util.Date;

import com.babySitter.model.dao.MotherDao;
import com.babySitter.model.dto.Mother;


public class MotherRegisterService {
//	private MotherDao motherDao;
//
//	public MotherRegisterService(MotherDao motherDao) {
//		this.motherDao = motherDao;
//	}
//
//	public void regist(RegisterRequest req) {
//		Mother member = motherDao.getMotherByEmail(req.getEmail());
//		if (member != null) {
//			throw new AlreadyExistingMotherException("dup email " + req.getEmail());
//		}
//		
//		Mother newMother = new Mother(req.getEmail(), req.getPassword(), req.getName(), new Timestamp(0));
//		motherDao.insert(newMother);
//	}
}
