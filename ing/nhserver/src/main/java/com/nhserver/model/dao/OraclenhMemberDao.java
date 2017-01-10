package com.nhserver.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.nhserver.model.dto.Member;
import com.nhserver.model.mapper.nhMemberMapper;

@Repository(value = "oraclenhMemberDao")
public class OraclenhMemberDao implements nhMemberDao {

	@Autowired
	@Qualifier("nhmemberMapper")
	private nhMemberMapper nhmemberMapper;

	public void insert(Member member) {
		nhmemberMapper.insertMember(member);
	}

	public List<Member> getList() {

		List<Member> members = nhmemberMapper.selectMembers();

		return members;
	}

	@Override
	public Member getMemberByIdAndPasswd(String id, String passwd) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", id);
		map.put("passwd", passwd);
		// Member member = sqlSessionTemplate.selectOne(
		// "com.mvcdemoweb.model.mapper.MemberMapper.selectMemberByIdAndPasswd",
		// map);
		Member member = nhmemberMapper.selectMemberByIdAndPasswd(map);
		return member;
	}

	@Override
	public Member getMemberById(String id) {
		Member member = nhmemberMapper.selectMemberById(id);
		return member;
	}

	@Override
	public void updateInstanceId(Member member) {
		nhmemberMapper.updateInstanceId(member);
	}

}
