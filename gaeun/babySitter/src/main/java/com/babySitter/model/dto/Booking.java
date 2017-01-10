package com.babySitter.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Booking implements Serializable{

	private int booking_no;
	private int child_no;
	private int sitter_no;
	private Date pick_date;
	private Date start_time;
	private Date end_time;
	private Date regdate;
	
	public int getBooking_no() {
		return booking_no;
	}
	public void setBooking_no(int booking_no) {
		this.booking_no = booking_no;
	}
	public int getChild_no() {
		return child_no;
	}
	public void setChild_no(int child_no) {
		this.child_no = child_no;
	}
	public int getSitter_no() {
		return sitter_no;
	}
	public void setSitter_no(int sitter_no) {
		this.sitter_no = sitter_no;
	}
	public Date getPick_date() {
		return pick_date;
	}
	public void setPick_date(Date pick_date) {
		this.pick_date = pick_date;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
