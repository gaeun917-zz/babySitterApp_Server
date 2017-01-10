package com.nhserver.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nhserver.common.Util;
import com.nhserver.model.dao.MemberDao;
import com.nhserver.model.dto.Member;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	
	//private MemberDao dao = new OracleMemberDao();
	@Autowired
	@Qualifier("oracleMemberDao")
	private MemberDao dao;
	public void setMemberDao(MemberDao memberDao) {
		this.dao = memberDao;
	}
	
	@RequestMapping(
		value = "/login.action", method = RequestMethod.GET)
	public String loginForm() {
		return "account/loginform";
		// /WEB-INF/views/ + account/loginform + .jsp
	}
	@RequestMapping(
		value = "/login.action", method = RequestMethod.POST)
	public String login(
		String memberId, String passwd, HttpSession session) {
		passwd = Util.getHashedString(passwd, "SHA-256");
		
		
		Member member = 
			dao.getMemberByIdAndPasswd(memberId, passwd);
		if (member != null) {
			//세션에 로그인 정보 저장
			session.setAttribute("loginuser", member);
			return "redirect:/home.action"; 
		} else {
			return "account/loginform";
		}		
		
	}
	@RequestMapping(
		value = "/logout.action", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginuser");//로그아웃
		
		return "redirect:/home.action";
	}
	
	
}
