package com.nhserver.model.mapper;

import com.nhserver.model.dto.Manbo;

public interface nhManboMapper {

	void insertManbo(Manbo manbo);
	Manbo selectRegdateByMemberId(String memberId);
//	List<Member> selectMembers();
//	Member selectMemberById(String memberId);
//	Member selectMemberByIdAndPasswd(
//	HashMap<String, Object> params);

	
}



