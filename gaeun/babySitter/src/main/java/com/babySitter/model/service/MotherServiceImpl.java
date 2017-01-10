package com.babySitter.model.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.babySitter.model.dao.MotherDao;
//import com.babySitter.model.dto.Mother;
//
//
////@Service + 서비스 인터페이스 상속(전부 @overwrite해야함) + DAO(데이터 접근) 
//@Service("motherService")
public class MotherServiceImpl{// implements MotherService {
//
//	@Autowired
//	@Qualifier("oracleMotherDao")
//	private MotherDao motherDao;
//
//	@Override
//	public void registerMother(Mother mother) {
//		motherDao.insert(mother);
//	}
//	
//	@Override
//	public Mother login(String email, String password) {
//		return motherDao.selectMotherByEmailAndPassword(email, password);
//	}
//	
//	@Override
//	public List<Mother> getAllMother() {
//		return motherDao.getList();
//	}
//
//	@Override
//	public Mother searchMotherByEmail(String email) {
//		return motherDao.getMotherByEmail(email);
//	}
//
//
//
//	public Mother getMotherByMotherNo(int mother_no) {
//		return motherDao.getMotherByMotherNo(mother_no);
//	}
//
//
//
}
