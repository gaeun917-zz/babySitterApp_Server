package com.babySitter.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.babySitter.model.dto.Age;
import com.babySitter.model.dto.Sitter;
import com.babySitter.model.dto.Skill;
import com.babySitter.model.mapper.SitterMapper;
import com.babySitter.model.dao.SitterDao;


@Repository(value = "oracleSitterDao")
public class OracleSitterDao implements SitterDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("sitterMapper")
	private SitterMapper sitterMapper;
	
	
	
	@Override
	public void insertSitter(Sitter sitter) {
		sitterMapper.insertSitter(sitter);
	}
	@Override
	public void insertSkills(Skill skill) {
		sitterMapper.insertSkills(skill);	
	}
	@Override
	public void insertAgeGroup(Age age) {
		sitterMapper.insertAgeGroup(age);	
	}



	
	
	@Override
	public List<Sitter> getSitterList() {
		List<Sitter> sitters = sitterMapper.selectSitterList();
		return sitters;
	}
	
	@Override
	public Sitter getSitterBySitterNo(int sitter_no) {
		Sitter sitter = sitterMapper.selectSitterBySitterNo(sitter_no);
		return sitter;
	}
	
	@Override
	public List<Sitter> getSkillsBySitterNo(int sitter_no) {
		List<Sitter> sitters = sitterMapper.selectSkillsBySitterNo(sitter_no);
		return sitters;
	}
	
	@Override
	public List<Sitter> getAgeGroupBySitterNo(int sitter_no) {
		List<Sitter> sitters = sitterMapper.selectAgeGroupBySitterNo(sitter_no);
		return sitters;
	}
	
	@Override
	public List<Sitter> selectBookingBySitterNo(int sitter_no) {
		List<Sitter> sitters = sitterMapper.selectBookingBySitterNo(sitter_no);
		return sitters;
	}
	@Override
	public List<Sitter> selectSitterBySkills(String skill_type) {
		List<Sitter> sitters = sitterMapper.selectSitterBySkills(skill_type);
		return sitters;
	}
	@Override
	public List<Sitter> selectSitterByQuality(String sitter_quality) {
		List<Sitter> sitters = sitterMapper.selectSitterByQuality(sitter_quality);
		return sitters;
	}

	
	

	
	@Override
	public void delectBySitterNo(int sitter_no) {
		sitterMapper.deleteSitterBySitterNo(sitter_no);			
	}

}
