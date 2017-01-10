package com.babySitter.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.babySitter.common.Util;
import com.babySitter.model.dao.ChildrenDao;
import com.babySitter.model.dao.ChildrenDao;
import com.babySitter.model.dto.Allergies;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Interest;
import com.babySitter.model.dto.Meal;
import com.babySitter.model.dto.Children;
import com.babySitter.model.service.ChildrenService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.babySitter.model.service.ChildrenService;


@Controller
@RequestMapping(value = "/children")
public class ChildrenController {

	
	
// 1. DAO & set  
	@Autowired
	@Qualifier("oracleChildrenDao")
	private ChildrenDao dao;	
	public void setChildrenDao(ChildrenDao childrenDao) {
		this.dao = childrenDao;
	}
	
// 2. Service: @Resource로 하명 수동으로 빈 등록해야함 -> @Autowired	
//	@Autowired
//	@Qualifier("childrenService")
//	private ChildrenService childrenService;

	
	
//	// insertChild는  account controller에 있음 
//	@RequestMapping(value = "list.action", method = RequestMethod.POST)
//	public String insertChildren() {	
//		// 1. 데이터 조회 (dao)
//		void children = dao.insertChildren(children);		
//		// 2. 데이터 저장 (jsp에서 사용할 수 있도록)
//		Gson gson = new Gson();
//		String json = gson.toJson(children);
//		// 3. 뷰 반환
//		return json;		
//	}
//	
	@RequestMapping(value = "insert1.action", method = RequestMethod.POST)
	public String insert(@RequestBody Children children) {	
		// 1. 데이터 조회 (dao)	
		dao.insertChildren(children);		
		return "child is inserted";		
	}
	
	@RequestMapping(value = "list1.action", method = RequestMethod.POST)
	public String insertAllergies(Allergies allergies) {	
		// 1. 데이터 조회 (dao)	
		dao.insertAllergies(allergies);		
		return "allergies insert";		
	}
	
	@RequestMapping(value = "list2.action", method = RequestMethod.POST)
	public String insertInterest(Interest interest) {	
		// 1. 데이터 조회 (dao)
		dao.insertInterest(interest);		
		return "allergies insert";		
	}
	
	@RequestMapping(value = "list3.action", method = RequestMethod.POST)
	public String insertMeal(Meal meal) {	
		// 1. 데이터 조회 (dao)
		dao.insertMeal(meal);			
		return "allergies insert";		
	}
	
	
	
	
}