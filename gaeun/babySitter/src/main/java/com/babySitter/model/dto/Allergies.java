package com.babySitter.model.dto;

import java.sql.Date;

public class Allergies{
	
	private int children_allergies_no;
	private int child_no;	
	private String allergie_name;
	private String note;

	
	
	public String getAllergie_name() {
		return allergie_name;
	}
	
	public void setAllergie_name(String allergie_name) {
		this.allergie_name = allergie_name;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public int getChild_no() {
		return child_no;
	}
	
	public void setChild_no(int child_no) {
		this.child_no = child_no;
	}
	
	public int getChildren_allergies_no() {
		return children_allergies_no;
	}
	
	public void setChildren_allergies_no(int children_allergies_no) {
		this.children_allergies_no = children_allergies_no;
	}


}
