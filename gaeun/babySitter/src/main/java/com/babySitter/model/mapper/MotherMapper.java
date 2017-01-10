package com.babySitter.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Payment;

public interface MotherMapper {

	
// MemberMapper.xml의 sql statement를 여기서 나열! 	

	void insertMother(Mother mother);
	void insertPayment(Payment payment);

	List<Mother> selectMotherList();
	
	Mother selectMotherByEmailAndPassword(HashMap<String, Object> params);
	Mother selectMotherByEmail(String email);
	Mother selectMotherByMotherNo(int motherNo);
	Payment selectPaymentByMotherNo(int motherNo);
	List<Children> selectChildrenByMotherNo(int motherNo);
// update는 void로 받음  
	void changePasswordByMotherNo(int motherNo);
	void deleteByMotherNo(int motherNo);
	void updateAddressAndPhoneByMotherNo(int motherNo);

	
	
	
	
//	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
//	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
//	int selectBoardMemberCountByBoardNo(int boardNo);
	
}



