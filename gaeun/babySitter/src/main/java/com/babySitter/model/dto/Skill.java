package com.babySitter.model.dto;

public class Skill {

	private int skill_no;
	private int sitter_no;
	private String skill_type;		

	
	public int getSkill_no() {
		return skill_no;
	}
	
	public void setSkill_no(int skill_no) {
		this.skill_no = skill_no;
	}
	
	public int getSitter_no() {
		return sitter_no;
	}
	
	public void setSitter_no(int sitter_no) {
		this.sitter_no = sitter_no;
	}
	
	public String getSkill_type() {
		return skill_type;
	}
	
	public void setSkill_type(String skill_type) {
		this.skill_type = skill_type;
	}

}
