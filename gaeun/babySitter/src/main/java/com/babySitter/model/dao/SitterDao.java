package com.babySitter.model.dao;

import java.util.List;

import com.babySitter.model.dto.Age;
import com.babySitter.model.dto.Sitter;
import com.babySitter.model.dto.Skill;

public interface SitterDao {

	void insertSitter(Sitter sitter);
	void insertAgeGroup(Age age);
	void insertSkills(Skill skill);
	
	List<Sitter> getSitterList();
	Sitter getSitterBySitterNo(int sitter_no);
	List<Sitter> getSkillsBySitterNo(int sitter_no);
	List<Sitter> getAgeGroupBySitterNo(int sitter_no);	
	List<Sitter> selectBookingBySitterNo(int sitter_no);
	List<Sitter> selectSitterBySkills(String skill_type);
	List<Sitter> selectSitterByQuality(String sitter_quality);
	

	void delectBySitterNo(int sitter_no);



}