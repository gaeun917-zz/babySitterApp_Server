package com.nhserver.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.nhserver.model.dto.Member;

public interface nhMemberMapper {

	void insertMember(Member member);
	List<Member> selectMembers();
	Member selectMemberById(String memberId);
	Member selectMemberByIdAndPasswd(
	HashMap<String, Object> params);
	void updateInstanceId(Member member);
}



