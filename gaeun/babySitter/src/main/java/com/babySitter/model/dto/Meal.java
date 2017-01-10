package com.babySitter.model.dto;

import java.sql.Date;

public class Meal{
	
	private int child_no;
	private int meal_no;	
	private Date breakfast;
	private Date lunch;
	private Date dinner;

	
	public Date getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Date breakfast) {
		this.breakfast = breakfast;
	}
	public Date getLunch() {
		return lunch;
	}
	public void setLunch(Date lunch) {
		this.lunch = lunch;
	}
	public Date getDinner() {
		return dinner;
	}
	public void setDinner(Date dinner) {
		this.dinner = dinner;
	}
	public int getChild_no() {
		return child_no;
	}
	public void setChild_no(int child_no) {
		this.child_no = child_no;
	}
	public int getMeal_no() {
		return meal_no;
	}
	public void setMeal_no(int meal_no) {
		this.meal_no = meal_no;
	}
	
	
}
