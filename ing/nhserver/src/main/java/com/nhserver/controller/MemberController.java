package com.nhserver.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.nhserver.common.Util;
import com.nhserver.model.dao.MemberDao;
import com.nhserver.model.dto.Member;

@Controller
@RequestMapping(value = "/member/")
public class MemberController {
	
	@Autowired
	@Qualifier("oracleMemberDao")
	private MemberDao dao;
	public void setMemberDao(MemberDao memberDao) {
		this.dao = memberDao;
	}
	
	@RequestMapping(
		value = "list.action", method = RequestMethod.GET)
	public String list(Model model) {
		//1. 데이터 조회 (dao 사용)
		List<Member> members = dao.getList();
		
		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
		model.addAttribute("members", members);
		
		//3. 뷰 반환
		return "member/list";
	}
	
//	@RequestMapping(
//		value = "list.action", method = RequestMethod.GET)
//	public ModelAndView list() {
//		//1. 데이터 조회 (dao 사용)
//		List<Member> members = dao.getList();
//		
//		//2. 데이터 저장 (jsp에서 사용할 수 있도록)
//		ModelAndView mav = new ModelAndView("member/list");
//		mav.addObject("members", members);
//		
//		//3. 뷰 반환
//		return mav;		
//	}
	
	@RequestMapping(
		value = "view.action", method = RequestMethod.GET)
	public String view(
		@RequestParam("memberid") String memberId, Model model) {
		
		if (memberId == null || memberId.length() == 0) {
			return "redirect:/member/list.action";
		}
		
		Member member = dao.getMemberById(memberId);
		model.addAttribute("member", member);
		
		return "member/view";
	}
	
	@RequestMapping(
		value = "register.action", method = RequestMethod.GET)
	public String registerForm(
		//스프링 태그 라이브러리를 사용하기 위해 구성한 전달인자 
		@ModelAttribute Member member) {
		return "member/registerform2";
	}
	@RequestMapping(
		value = "register.action", method = RequestMethod.POST)
	public String register(
		Member member, BindingResult result) {		
		if (result.hasErrors()) {
			return "member/registerform2";
		}
		
		member.setPasswd(
			Util.getHashedString(member.getPasswd(), "SHA-256"));
		
		dao.insert(member);
		
		return "redirect:/member/list.action";
		
	}

}









