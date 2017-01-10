package com.babySitter.model.dto;

import java.io.Serializable;

public class Payment implements Serializable {

	private int mother_no;
	private int cvv;
	private String first_name;
	private String last_name;
	private int card_number;
	private int expiration;
	private String billing_address;		
	
		
	public int getMother_no() {
		return mother_no;
	}
	public void setMother_no(int mother_no) {
		this.mother_no = mother_no;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getCard_number() {
		return card_number;
	}
	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}
	public int getExpiration() {
		return expiration;
	}
	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}
	public String getBilling_address() {
		return billing_address;
	}
	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}
	
	
	
	
}
