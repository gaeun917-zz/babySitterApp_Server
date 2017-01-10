package com.babySitter.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Payment;
import com.babySitter.model.mapper.MotherMapper;



// mapper에서 만든 sql문을 받아옴->
//Dao에서 메소드로 paramete 및 리턴 설정 
// -> 컨트롤러에서 Dao 불러와서 기능 설정!


// 경로
@Repository(value = "oracleMotherDao")
public class OracleMotherDao implements MotherDao {
//	sqlSession을 대체함: 
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("motherMapper")
	private MotherMapper motherMapper;

	// 파라미터 Mother dto로 받아서 set method 
	private Map<String, Mother> map = new HashMap<>();
	
	
	
	
	
///////////   INSERT - void  ////////////////////////////////
	public void insert(Mother mother) {		
		motherMapper.insertMother(mother);
	}
	

	@Override
	public void insertPayment(Payment payment) {
		motherMapper.insertPayment(payment);
		
	}


//////////   SELECT 	////////////////////////////
	
	public List<Mother> getList() {
		List<Mother> mothers = motherMapper.selectMotherList();		
		return mothers;
	}
	
	public Mother getMotherByEmail(String email) {	
		Mother mother = motherMapper.selectMotherByEmail(email);
		return mother;
	}
	
	// 파라미터 두개 받으면 hashmap으로 만들어서 mapper method에 파라미터로 넘겨줌 
	public Mother selectMotherByEmailAndPassword(HashMap<String,Object> param) {
		
		Mother mother = motherMapper.selectMotherByEmailAndPassword(param);
		return mother;
	}
	
	@Override
	public List<Children> selectChildrenByMotherNo(int mother_no) {
		List<Children> children = motherMapper.selectChildrenByMotherNo(mother_no);	
		return children;
	}

	@Override
	public Mother getMotherByMotherNo(int motherNo) {
		Mother mother = motherMapper.selectMotherByMotherNo(motherNo);
		return mother;
	}

	@Override
	public Payment selectPaymentByMotherNo(int mother_no) {
		Payment payment = motherMapper. selectPaymentByMotherNo(mother_no);
		return payment;
	}

	
	
	
	
//////////   UPDATE - void 	/////////////////////////////		
	
	public void changePasswordByMotherNo(int motherNo) {
		motherMapper.changePasswordByMotherNo(motherNo);		
	}

	@Override
	public void delectByMotherNo(int mother_no) {
		motherMapper.deleteByMotherNo(mother_no);	
	}
		
	@Override
	public void updateAddressAndPhoneByMotherNo(int mother_no) {
		motherMapper.updateAddressAndPhoneByMotherNo(mother_no);	
		
	}

	
	
//	@Override
//	public void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember) {
//		motherMapper.insertBoardMemberByBoardNoMemberNo(boardMember);
//	}
//
//
//	@Override
//	public List<BoardMember> selectBoardMemberByBoardNo(int boardNo) {
//		return motherMapper.selectBoardMemberByBoardNo(boardNo);
//	}
//
//
//	@Override
//	public int selectBoardMemberCountByBoardNo(int boardNo) {
//		return motherMapper.selectBoardMemberCountByBoardNo(boardNo);
//	}
	

}


