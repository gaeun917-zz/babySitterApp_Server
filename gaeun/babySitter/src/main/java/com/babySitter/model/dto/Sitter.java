package com.babySitter.model.dto;

import java.io.Serializable;
import java.util.List;

public class Sitter implements Serializable {

	
	
	   private int booking_no;
	    private String name;
	    private int sitter_age;
	    private String bio;
	    private String school;
	    private String skills;
	    private String email;
	    private int rating;

	    private String address;
	    private String profile;
	    private String gender;

	    private String sitter_quality;
	    private String sitter_quality2;
	    private String sitter_quality3;
	
	
	
	  public int getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSitter_age() {
		return sitter_age;
	}
	public void setSitter_age(int sitter_age) {
		this.sitter_age = sitter_age;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSitter_quality() {
		return sitter_quality;
	}
	public void setSitter_quality(String sitter_quality) {
		this.sitter_quality = sitter_quality;
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


	

	
	
	
	private List<Age> age;
	
	public List<Age> getAge() {
		return age;
	}
	public void setAge(List<Age> age) {
		this.age = age;
	}
	
	
	private List<Booking> booking;
	
	public List<Booking> getBooking() {
		return booking;
	}
	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}
	 
	
	private List<Skill> skill;
	
	public List<Skill> getSkill() {
		return skill;
	}
	public void setSkill(List<Skill> skill) {
		this.skill = skill;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
