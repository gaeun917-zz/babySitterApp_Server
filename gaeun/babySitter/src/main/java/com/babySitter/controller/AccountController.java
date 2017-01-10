package com.babySitter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babySitter.common.Util;
import com.babySitter.model.dao.ChildrenDao;
import com.babySitter.model.dao.MotherDao;
import com.babySitter.model.dao.SitterDao;
import com.babySitter.model.dto.Children;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Payment;
import com.babySitter.model.dto.Sitter;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.service.MotherService;
import com.babySitter.model.service.MotherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
	// private MotherDao dao = new OracleMotherDao();

	// 의존주입: Mother은 인터페이스 -> 오라클이 마더Dao(interface)
	@Autowired
	@Qualifier("oracleMotherDao")
	private MotherDao dao;

	public void setMotherDao(MotherDao motherDao) {
		this.dao = motherDao;
	}

	@Autowired
	@Qualifier("oracleChildrenDao")
	private ChildrenDao childrendao;

	public void setChildrenDao(ChildrenDao childrenDao) {
		this.childrendao = childrenDao;
	}

	@Autowired
	@Qualifier("oracleSitterDao")
	private SitterDao sitterdao;

	public void setSitterDao(SitterDao sitterDao) {
		this.sitterdao = sitterDao;
	}
	
	// @Autowired
	// @Qualifier("motherService")
	// private MotherService motherService;

	// --------------- 회원가입 ---------------------------------------------

	// 메소드 맵핑 주소
	// bindingResult : .hasErrors 사용할때 파라미터로 쓴다.
	// @Valid 유효성 검사
	// @RequestMapping(value = "register.action", method = RequestMethod.POST)
	// public String register(@Valid @ModelAttribute("mother") Mother mother,
	// BindingResult result) {
	//
	// if(result.hasErrors()){
	// return "mother/registerform2";
	// }
	// mother.setPassword(Util.getHashedString(mother.getPassword(),
	// "SHA-256"));
	// dao.insert(mother);
	// return
	// "redirect:/mother/registerSuccess.action?email="+mother.getEmail();
	// }

	// --------------- Gson version -----------------------------------

	// ------------------- 회원 가입 ---------------------------------------------

	// 에러나면 responseBody 뺄것, writer 살릴것
	// JSON : 데이터를 편하게 주고 받는 형태로 바꿔줌
	// 파라미터에 Mother 받는거는 MotherDao에서 insert를 mother 줬기 때문에
	// 안드로이드에서 요청이 간다. requestBody랑 post랑 상관없고,
	// 안드로이드에서는 제이슨으로 바꿔서 보냄 -
	// 레지스터의 Mother 객체를 Gson으로 담고, @RequestBody로
	// 객체를 안드로이드로 넘겨줄때 Gson

	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public void registerPost(@RequestBody Mother mother, HttpServletResponse resp, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("error in mother register");
		}
		mother.setPassword(Util.getHashedString(mother.getPassword(), "SHA-256"));
		dao.insert(mother);
	}

	// -------------- child register -------------------

	@RequestMapping(value = "registerchild.action", method = RequestMethod.POST)
	public void registerChildrenPost(@RequestBody Children children, HttpServletResponse resp, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("error in child register");
		}
		childrendao.insertChildren(children);
	}

	
	// -------------- payment register -------------------
	
	@RequestMapping(value = "payment.action", method = RequestMethod.POST)
	public void registerPaymentPost(@RequestBody Payment payment, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("error in payment register");
		}
		dao.insertPayment(payment);
	}

	
	
	
	
	// ---------------------- sitter register -----------------
	@ResponseBody
	@RequestMapping(value = "registersitter.action", method = RequestMethod.POST)
	public void registerSitterPost(@RequestBody Sitter sitter, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("error in payment register");
		}
		sitterdao.insertSitter(sitter);
	}

	// ---------------------------- 로그인

	@ResponseBody
	@RequestMapping(value = "login.action")
	public String loginPost(String email, String password) {
		password = Util.getHashedString(password, "SHA-256");
		System.out.println("in login Post : email = " + email + " password :" + password);
		

		HashMap<String,Object> param = new HashMap<>();
		param.put("email", email);
		param.put("password", password);		
		Mother mother = dao.selectMotherByEmailAndPassword(param);		
		
		if (mother != null) {
			Gson gson = new Gson();
			String json = gson.toJson(mother);
			return json;
		} else {
			System.out.println("email and password is null");
			return "fail";
		}

	}

	// @RequestMapping(value = "/login.action", method = RequestMethod.POST)
	// @ResponseBody // responseBody: 메소드가 반환하는 값이 페이지 이름이 아니고, 데이터 그 자체임!
	// public String login(String email, String password, HttpSession session) {
	//
	// password = Util.getHashedString(password, "SHA-256");
	// Mother mother = motherService.login(email, password);
	// if (mother != null) {// 로그인 정보 받아서 세션에 로그인 정보 저장
	// session.setAttribute("loginuser", mother);
	// List<Mother> mother2 =
	// motherService.searchMotherByEmail(mother.getEmail());
	// //if( mother != null){ // 차일드 딸려 있으면 셋
	// session.setAttribute("userpages", pages);
	// }
	// return "success";
	// } else {
	// return "fail";
	// }
	// }

	// -------------------- email 중복검사 ----------------------------------
	@RequestMapping(value = "/check-duplicate.action", method = RequestMethod.POST)
	@ResponseBody // responseBody: 메소드가 반환하는 값이 페이지 이름이 아니고, 데이터 그 자체임!
	public String checkDuplicate(String email) {
		Mother mother = dao.getMotherByEmail(email);

		if (mother != null) {
			return "fail";
		} else {
			return "success";
		}
	}

	// ------------------- 소셜 로그 -----------------------------------------

	@RequestMapping(value = "/facebookLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String facebooklogin(String nickname, HttpSession session) {
		// password = Util.getHashedString(passwd, "SHA-256");

		session.setAttribute("facebookLoginuser", nickname);
		// Member member = memberService.login(email, password);
		// if (member != null) {
		// //세션에 로그인 정보 저장
		// session.setAttribute("loginuser", mother);
		// return "redirect:/home.action";
		// } else {
		// return "account/loginform";
		// }
		return "success";
	}

	@RequestMapping(value = "/kakaoLogin.action", method = RequestMethod.POST)
	@ResponseBody
	public String kakaologin(String nickname, HttpSession session) {
		session.setAttribute("kakaoLoginuser", nickname);
		return "success";
	}

	// ------------------- 소셜 로그 아웃 -----------------------------------------
	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginuser");// 로그아웃
		session.removeAttribute("userpages");// 로그아웃
		return "redirect:/home.action";
	}

	@RequestMapping(value = "/facebookLogout.action", method = RequestMethod.GET)
	public String facebookLogout(HttpSession session) {
		session.removeAttribute("facebookLoginuser");// 로그아웃
		return "redirect:/home.action";
	}

	@RequestMapping(value = "/kakaoLogout.action", method = RequestMethod.GET)
	public String kakaoLogout(HttpSession session) {
		session.removeAttribute("kakaoLoginuser");// 로그아웃
		return "redirect:/home.action";
	}

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String loginForm() {
		return "account/loginform";
		// /WEB-INF/views/ + account/loginform + .jsp
	}

	// @RequestMapping(value = "/pageList.action", method = RequestMethod.GET,
	// produces = "text/plain; charset=utf8")
	// @ResponseBody
	// public String getpageList(HttpSession session, HttpServletResponse
	// response) {
	//
	// Mother mother = (Mother) session.getAttribute("loginuser");
	// if(mother == null){
	// return "<hr><li style='text-align: center'>Please Login...</li><hr>";
	// }
	//
	// List<Page> pages =
	// pageService.searchPageNoByMemberNo(mother.getMother_no());
	// if(pages == null){
	// return "<hr><li style='text-align: center'>No Search Your Room</li><hr>";
	// }
	//
	// String html = "";
	// for(Page page : pages){
	// System.out.println(page.getName());
	// PageMenu menu =
	// pageService.selectPageMenuByPageNoNotice(page.getPageNo());
	// System.out.println(menu.getMenuNo());
	// html += "<hr><li style='text-align: center'><a
	// href=\"javascript:window.open('/studyCafe/page/board/list.action?menuno="
	// +
	// menu.getMenuNo() +
	// "&memberpageno=" +
	// page.getPageNo() +
	// "', '', 'width=1200, height=1000, resizable=yes');" +
	// "\">"+ page.getName() +"</a></li><hr>";
	// }
	// return html;
	//
	// }
}
