package com.babySitter.model.dao;

import java.util.HashMap;
import java.util.List;

import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Payment;

public interface MotherDao {

//OracleMemberDao의 function을 여기서 나열! 

	
	void insert(Mother mother); //registerPost
	void insertPayment(Payment payment);
	
	List<Mother> getList();
	// 파라미터 2개 hashmap
	Mother selectMotherByEmailAndPassword(HashMap<String,Object> param);
	Mother getMotherByEmail(String email);
	Mother getMotherByMotherNo(int mother_no);
	Payment selectPaymentByMotherNo(int mother_no);
	List<Children> selectChildrenByMotherNo(int mother_no);

	void changePasswordByMotherNo(int mother_no);
	void delectByMotherNo(int mother_no);
	void updateAddressAndPhoneByMotherNo(int mother_no);
//	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
//	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
//	int selectBoardMotherCountByBoardNo(int boardNo);
	
	
}