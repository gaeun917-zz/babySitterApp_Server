//package com.babySitter.model.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.babySitter.model.dao.ChildrenDao;
//import com.babySitter.model.dao.MotherDao;
//import com.babySitter.model.dto.Children;
//import com.babySitter.model.dto.Mother;
//
//
////@Service + 서비스 인터페이스 상속(전부 @overwrite해야함) + DAO(데이터 접근) 
////@Service("childrenService")
//public class ChildrenServiceImpl implements ChildrenService {
//
//	
//	
//	@Autowired
//	@Qualifier("oracleMotherDao")
//	private ChildrenDao childrenDao;
//
//	@Override
//	public void registerChildren(Children children) {
//		childrenDao.insert(children);
//	}
//	
//
//	@Override
//	public List<Children> searchChildrenByEmail(String email) {
//		return childrenDao.getChildrenByEmail(email);
//	}
//
//
//	public  List<Children> getChildrenByMotherNo(int mother_no) {
//		return childrenDao.getChildrenByMotherNo(mother_no);
//	}
//
//	public  Children getChildrenByChildrenNo(int children_no) {
//		return childrenDao.getChildrenByChildrenNo(children_no);
//	}
//
//
//	@Override
//	public List<Children> getAllChildren() {
//		return childrenDao.getList();
//	}
//
//
//	@Override
//	public Children getChildrenByBookingNo(int booking_no) {
//		return childrenDao.getChildrenByBookingNo(booking_no);
//	}
//
//
//
//
//	@Override
//	public void insertBoardMemberByBoardNoMemberNo(BoardMember boardMember) {
//		memberDao.insertBoardMemberByBoardNoMemberNo(boardMember);
//	}
//
//	@Override
//	public List<BoardMember> selectBoardMemberByBoardNo(int boardNo) {
//		return memberDao.selectBoardMemberByBoardNo(boardNo);
//	}
//
//	@Override
//	public int selectBoardMemberCountByBoardNo(int boardNo) {
//		return memberDao.selectBoardMemberCountByBoardNo(boardNo);
//	}
//
//
//}
