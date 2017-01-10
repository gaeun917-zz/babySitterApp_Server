package com.nhserver.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Member;
import com.nhserver.model.mapper.MemberMapper;

@Repository(value = "oracleMemberDao")
public class OracleMemberDao implements MemberDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("memberMapper")
	private MemberMapper memberMapper;
	
	public void insert(Member member) {
//		sqlSessionTemplate.insert(
//			"com.mvcdemoweb.model.mapper.MemberMapper.insertMember",
//			member);MemberDao.java
		
		memberMapper.insertMember(member);
	}
	public List<Member> getList() {
		
//		List<Member> members = sqlSessionTemplate.selectList(
//			"com.mvcdemoweb.model.mapper.MemberMapper.selectMembers");
		
		List<Member> members = memberMapper.selectMembers();
		
		return members;
	}

	public Member getMemberById(String id) {
//		Member member = sqlSessionTemplate.selectOne(
//			"com.mvcdemoweb.model.mapper.MemberMapper.selectMemberById", id);	
		Member member = memberMapper.selectMemberById(id);
		return member;
	}

	public Member getMemberByIdAndPasswd(String id, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", id);
		map.put("passwd", passwd);
//		Member member = sqlSessionTemplate.selectOne(
//			"com.mvcdemoweb.model.mapper.MemberMapper.selectMemberByIdAndPasswd", 
//			map);	
		Member member = memberMapper.selectMemberByIdAndPasswd(map);
		return member;
	}


	

}









