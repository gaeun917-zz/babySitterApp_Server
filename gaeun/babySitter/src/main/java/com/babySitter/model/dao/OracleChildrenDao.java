package com.babySitter.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.babySitter.model.dto.Allergies;
import com.babySitter.model.dto.Booking;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Interest;
import com.babySitter.model.dto.Meal;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Payment;
import com.babySitter.model.mapper.ChildrenMapper;
import com.babySitter.model.mapper.MotherMapper;

// mapper에서 만든 sql문을 받아옴-> Dao에서 메소드로 paramete 및 리턴 설정 
// -> 컨트롤러에서 Dao 불러와서 기능 설정!
@Repository(value = "oracleChildrenDao")
public class OracleChildrenDao implements ChildrenDao {
//	sqlSession을 대체함: 
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	@Qualifier("childrenMapper")
	private ChildrenMapper childrenMapper;
	// 파라미터 Mother dto로 받아서 set method 
	
	
	
	private Map<String, Mother> map = new HashMap<>();

	@Override
	public void insertChildren(Children children) {
		 childrenMapper.insertChildren(children);	
	}

	@Override
	public void insertAllergies(Allergies allergies) {
		childrenMapper.insertAllergies(allergies);	
	}

	@Override
	public void insertInterest(Interest interest) {
		childrenMapper.insertInterest(interest);	
	}

	@Override
	public void insertMeal(Meal meal) {
		childrenMapper.insertMeal(meal);
		
	}

	@Override
	public List<Children> getList() {
		List<Children> children = childrenMapper.selectChildrenList();
		return children;
	}

	@Override
	public Children getChildrenByChildrenNo(int child_no) {
		Children children = childrenMapper. selectChildrenByChildrenNo(child_no);
		return children;
	}

	@Override
	public Meal selectMealByChildNo(int child_no) {
		Meal meal = childrenMapper.selectMealByChildNo(child_no);
		return meal;
	}

	@Override
	public List<Allergies> selectChildrenAllergiesByChildNo(int child_no) {
		List<Allergies> allergies = childrenMapper.selectAllergiesByChildNo(child_no);
		return allergies;
	}

	@Override
	public List<Interest> selectInterestByChildNo(int child_no) {
		List<Interest> interest = childrenMapper.selectInterestByChildNo(child_no);
		return interest;
	}

	@Override
	public List<Booking> selectBookingByChildNo(int child_no) {
		List<Booking> booking = childrenMapper.selectBookingByChildNo(child_no);
		return booking;
	}

	@Override
	public void updateMealByChildNo(int child_no) {
		childrenMapper.updateMealByChildNo(child_no);
	}
	
	
	
	
	

}


