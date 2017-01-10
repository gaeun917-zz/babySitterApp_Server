package com.nhserver.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Manbo;
import com.nhserver.model.mapper.nhManboMapper;

@Repository(value = "oraclenhManboDao")
public class OraclenhManboDao implements nhManboDao {
	
//	@Autowired
//	@Qualifier("sqlSessionTemplate")
//	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("nhManboMapper")
	private nhManboMapper manboMapper;
	
	public void insert(Manbo manbo) {
//		sqlSessionTemplate.insert(
//			"com.mvcdemoweb.model.mapper.MemberMapper.insertMember",
//			member);MemberDao.java
		
		manboMapper.insertManbo(manbo);
	}
	
	@Override
	public Manbo getRegDateByMemberId(String memberId) {
		// TODO Auto-generated method stub
		Manbo manbo = manboMapper.selectRegdateByMemberId(memberId);
		return manbo;

	}
//	public List<Member> getList() {
//		
////		List<Member> members = sqlSessionTemplate.selectList(
////			"com.mvcdemoweb.model.mapper.MemberMapper.selectMembers");
//		
//		List<Member> members = nhmemberMapper.selectMembers();
//		
//		return members;
//	}
//
//	public Member getMemberById(String id) {
////		Member member = sqlSessionTemplate.selectOne(
////			"com.mvcdemoweb.model.mapper.MemberMapper.selectMemberById", id);	
//		Member member = memberMapper.selectMemberById(id);
//		return member;
//	}
//
//	@Override
//	public Member getMemberByIdAndPasswd(String id, String passwd) {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("memberId", id);
//		map.put("passwd", passwd);
////		Member member = sqlSessionTemplate.selectOne(
////			"com.mvcdemoweb.model.mapper.MemberMapper.selectMemberByIdAndPasswd", 
////			map);	
//		Member member = nhmemberMapper.selectMemberByIdAndPasswd(map);
//		return member;
//	}
//	
//	@Override
//	public Member getMemberById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



}









