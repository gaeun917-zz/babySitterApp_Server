package com.babySitter.model.dto;

public class Interest{
	
	private int interest_no;	
	private int child_no;
	private String interest_type;

	
	public String getInterest_type() {
		return interest_type;
	}
	public void setInterest_type(String interest_type) {
		this.interest_type = interest_type;
	}
	public int getChild_no() {
		return child_no;
	}
	public void setChild_no(int child_no) {
		this.child_no = child_no;
	}
	public int getInterest_no() {
		return interest_no;
	}
	public void setInterest_no(int interest_no) {
		this.interest_no = interest_no;
	}
	
	
}
