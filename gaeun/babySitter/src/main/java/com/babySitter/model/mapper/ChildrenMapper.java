package com.babySitter.model.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.babySitter.model.dto.Allergies;
import com.babySitter.model.dto.Booking;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Interest;
import com.babySitter.model.dto.Meal;

public interface ChildrenMapper {

	
// ChildrenMapper.xml의 sql statement를 여기서 나열! 	

	void insertChildren(Children children);
	void insertAllergies(Allergies allergies);
	void insertInterest(Interest interest);
	void insertMeal(Meal meal);
	
	List<Children> selectChildrenList();
	Children selectChildrenByChildrenNo(int child_no);
	
	Meal selectMealByChildNo(int child_no);	
	List<Allergies> selectAllergiesByChildNo(int child_no);
	List<Interest> selectInterestByChildNo(int child_no);
	List<Booking> selectBookingByChildNo(int child_no);

	
	void updateMealByChildNo(int child_no);
	
	

	
	
}



