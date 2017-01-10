package com.babySitter.model.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Mother{
	
		
	private int mother_no;
	@NotEmpty(message ="*이메일을를 넣어주세요")
	@Email(message ="*이메일 형식을 넣어주세요")
	private String email;
	
	@NotEmpty(message ="*비밀번호를 넣어주세요")
	@Pattern(regexp = "[A-Za-z0-9]{6,15}", message = "알파벳, 숫자 조합으로 6~15자리")
	private String password;
	private String confirm_password;	
	private int phone;
	private int photo;

	private String address;
	private String first_name;
	private String last_name;
	private String facebook_id;
	private Timestamp reg_date;
	
	private String sitter_quality1;
	private String sitter_quality2;
	private String sitter_quality3;
	

	
	public int getMother_no() {
		return mother_no;
	}
	public void setMother_no(int mother_no) {
		this.mother_no = mother_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getPhoto() {
		return photo;
	}
	public void setPhoto(int photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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

	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	
	
	public String getSitter_quality1() {
		return sitter_quality1;
	}
	public void setSitter_quality1(String sitter_quality1) {
		this.sitter_quality1 = sitter_quality1;
	}
	public String getSitter_quality2() {
		return sitter_quality2;
	}
	public void setSitter_quality2(String sitter_quality2) {
		this.sitter_quality2 = sitter_quality2;
	}
	public String getSitter_quality3() {
		return sitter_quality3;
	}
	public void setSitter_quality3(String sitter_quality3) {
		this.sitter_quality3 = sitter_quality3;
	}

	
	public Mother(){}
	
	public Mother(String email, String password, String first_name, String last_name, Timestamp reg_date){
		this.email = email;
		this.password = password; 
		this.first_name = first_name;
		this.last_name = last_name;
		this.reg_date = reg_date;	
	}	
	
	
//	 연결된 사항 리스트로 가져옴 
	private List<Children> children;

	public List<Children> getChildren() {
		return children;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
	}

	
	
	private List<Payment> payment;

	public List<Payment> getPayment() {
		return payment;
	}
	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

}
