package com.nhserver.model.dao;

import java.util.List;

import com.nhserver.model.dto.Member;

public interface nhMemberDao {

	void insert(Member member);

	List<Member> getList();

	Member getMemberById(String id);

	Member getMemberByIdAndPasswd(String id, String passwd);
	
	void updateInstanceId(Member member);

}