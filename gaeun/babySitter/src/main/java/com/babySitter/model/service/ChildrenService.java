package com.babySitter.model.service;

import java.util.List;

import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;

public interface ChildrenService {

	void registerChildren(Children children);
	List<Children> getAllChildren();
	List<Children> searchChildrenByEmail(String email);
	List<Children> getChildrenByMotherNo(int mother_no);
	Children getChildrenByChildrenNo(int children_no);
	Children getChildrenByBookingNo(int booking_no);
	
//	void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember);
//	List<BoardMember> selectBoardMemberByBoardNo(int boardNo);
//	int selectBoardMemberCountByBoardNo(int boardNo);
}
