package com.babySitter.model.dao;

import java.util.List;

import com.babySitter.model.dto.Allergies;
import com.babySitter.model.dto.Booking;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Interest;
import com.babySitter.model.dto.Meal;

public interface ChildrenDao {

//OracleMemberDao의 function을 여기서 나열! 

	// List<Children>는   children 여러명 모을때 
	// Children은 child 한명 모든 정보 받을떄 
	void insertChildren(Children children);//account
	void insertAllergies(Allergies allergies);
	void insertInterest(Interest interest);
	void insertMeal(Meal meal);
	
	
	List<Children> getList();
	Children getChildrenByChildrenNo(int children_no);
	
	Meal selectMealByChildNo(int child_no);
	List<Allergies> selectChildrenAllergiesByChildNo(int child_no);
	List<Interest> selectInterestByChildNo(int child_no);
	List<Booking> selectBookingByChildNo(int child_no);
// mother  Dao에 이 method가 있음 	
//	List<Children> getChildrenByMotherNo(int mother_no);
	

//	Children getChildrenByBookingNo(int booking_no);
	
	void updateMealByChildNo(int child_no);

	

	
	
}