package com.babySitter.model.mapper;

import java.util.List;

import com.babySitter.model.dto.Age;
import com.babySitter.model.dto.Sitter;
import com.babySitter.model.dto.Skill;

public interface SitterMapper {

	void insertSitter(Sitter sitter);
	void insertAgeGroup(Age age);
	void insertSkills(Skill skill);
	
	List<Sitter> selectSitterList();
	Sitter selectSitterBySitterNo(int sitter_no);
	List<Sitter> selectSkillsBySitterNo(int sitter_no);
	List<Sitter> selectAgeGroupBySitterNo(int sitter_no);	
	List<Sitter> selectBookingBySitterNo(int sitter_no);
	List<Sitter> selectSitterBySkills(String skill_type);
	List<Sitter> selectSitterByQuality(String sitter_quality);

	void deleteSitterBySitterNo(int sitter_no);
	
	
	
}
