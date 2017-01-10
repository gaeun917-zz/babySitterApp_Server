package com.babySitter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.babySitter.common.Util;
import com.babySitter.model.dao.ChildrenDao;
import com.babySitter.model.dao.MotherDao;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.service.MotherService;
import com.google.gson.Gson;


@Controller
@RequestMapping(value = "/mother/")
public class MotherController {

	
// 1. DAO & set  
	@Autowired
	@Qualifier("oracleMotherDao")
	private MotherDao dao;	
	public void setMotherDao(MotherDao motherDao) {
		this.dao = motherDao;
	}
	
	@Autowired
	@Qualifier("oracleChildrenDao")
	private ChildrenDao childrendao;
	public void setChildrenDao(ChildrenDao ChildrenDao) {
		this.childrendao = ChildrenDao;
	}
// 2. Service: @Resource로 하명 수동으로 빈 등록해야함 -> @Autowired	
//	@Autowired
//	@Qualifier("motherService")
//	private MotherService motherService;

	
// 전체 마더 리스트 보여줌 
	//안드로이드 보내
	@ResponseBody 
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public String list() {
		// 1. 데이터 조회 (dao)
		List<Mother> mothers = dao.getList();		
		// 2. 데이터 저장 (jsp에서 사용할 수 있도록)
		Gson gson = new Gson();
		String json = gson.toJson(mothers);
		// 3. 뷰 반환: 리스트를 반환해야함. 
		return json;		
	}
	

	
//	엄마 넘버로 아이 리스트 불러옴 
	
//	@ResponseBody
//	@RequestMapping(value = "view.action", method = RequestMethod.GET)
//	public String view(@RequestParam("email") String email, Model model) {
//		
//		if (email == null || email.length() == 0) {
//			return "redirect:/mother/list.action";
//			}	
//		Mother mother = dao.getMotherByEmail(email);
//		
//		return "mother/view";
//	}

	@ResponseBody
	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public String viewChildrenByMotherNo(int mother_no) {
		// 현재 로그인 된 엄마의 no get 				
		List<Children> children = dao.selectChildrenByMotherNo(mother_no);
		Gson gson = new Gson();
		String json = gson.toJson(children);
		return json;
	}
	
	
	
	
//	//  ----------------------- 마이 페이지 코드 ------------------------------------------
//	@RequestMapping(value = "mypage.action", method = RequestMethod.GET)
//	public String myPage( Model model, HttpSession session) {
//		
//		Mother mother  = (Mother) session.getAttribute("loginuser");
//		dao.getMotherByMotherNo(mother.getMother_no());
//		model.addAttribute("mother", mother);
//		return "mother/mypage";		
//	}
//	
//	
//	@RequestMapping(value = "changepassword.action", method = RequestMethod.POST)
//	@ResponseBody
//	public String changePassword(String oldPasswd, String newPasswd, HttpSession session) {
//		
//		boolean success = false;
//		// 1. 세션의 사용자 읽기 + 현재패스워드=oldPasswd 구하기 
//		Mother mother  = (Mother) session.getAttribute("loginuser");		
//		oldPasswd = Util.getHashedString(oldPasswd, "SHA-256");			
//		// 2. 세션 사용자 이메일과 oldPasswd로 정보 조회
//		Mother mother2 =  dao.getMotherByEmailAndPassword(mother.getEmail(), oldPasswd);
//
//		if (mother2 != null) {
//			// 3-1. 2의 조회가 성공하면 아이디와 newPasswd를 이용해서 비밀번호 변경
//			mother2.setPassword(Util.getHashedString(newPasswd, "SHA-256"));
//			dao.changePassword(mother2);
//			return "success";
//		} else {	//3-2. 2의 조회가 실패하면 실패 메시지 전송 
//			return "fail:invalid old password";
//		}
//	}
//	
//	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
//	public String delete(HttpSession session) {		
//		
//		Mother mother  = (Mother) session.getAttribute("loginuser");		
//		dao.delectByMotherNo(mother.getMother_no());
//		session.removeAttribute("loginuser");
//		return "redirect:/";
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	// 단순 넘어가기 코드 
//	// ---------- 회원 가입 button-> term -> 약관 동의-> 회원 등록페이지 --------------
//		@RequestMapping(value = "term.action", method = RequestMethod.GET)
//		public String term(@ModelAttribute("mother") Mother mother) {
//			return "mother/term";
//		}
//		
//		
////		@ModelAttribute: 스프링 태그 라이브러리를 사용하기 위해 구성한 전달인자 
//		@RequestMapping(value = "register.action", method = RequestMethod.GET)
//		public String registerForm(@ModelAttribute("mother")Mother mother) {
//			//mother.setRegDate(new Timestamp(new Date().getTime()));	
//			return "mother/registerform2";
//		}
//
//
//		@RequestMapping(value = "registerSuccess.action", method = RequestMethod.GET)
//		public String registerSuccess(String email, Model model) {
//			Mother mother = dao.getMotherByEmail(email);
//			model.addAttribute("mother", mother);
//			return "mother/registerSuccess";
//		}
//
//		
//		@RequestMapping(value = "registerSuccess.action", method = RequestMethod.POST)
//		public String registerSuccess2(@ModelAttribute Mother mother) {
//			return "mother/registerSuccess";
//		}
//	
//	
//	
	
}