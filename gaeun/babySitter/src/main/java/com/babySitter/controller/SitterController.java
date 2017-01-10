package com.babySitter.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.babySitter.common.Util;
import com.babySitter.model.dao.SitterDao;
import com.babySitter.model.dto.Age;
import com.babySitter.model.dto.Mother;
import com.babySitter.model.dto.Sitter;
import com.babySitter.model.dto.Skill;

@Controller
@RequestMapping(value = "/sitter")
public class SitterController{
//	@Autowired
//	@Qualifier("sitterService")
//	private SitterService sitterService;

	@Autowired
	@Qualifier("oracleSitterDao")
	private SitterDao sitterdao;
	public void setSitterDao(SitterDao sitterDao) {
		this.sitterdao = sitterDao;
	}	
	// http 객체에 응답 path(mapping) body(contents) 할 수 있는 형식으로 변환 	
	// request(post): 데이터베이스에 저장할때 mapping 을 부르면 requestBody 데이터를 java format으로  return(
	// responseBody (get) :보여줄때   convert the data(List<user>) into json foramt and send it to clients	
	// Restcontroller: 
	// hashmap만들어서 넣겨줌? 
	
	
			@RequestMapping(value = "apply.action", method = RequestMethod.POST)
			@ResponseBody
				public void applySitterPost(@RequestBody Sitter sitter, 						
											HttpServletResponse resp, BindingResult result) {
							
					if(result.hasErrors()){
						System.out.println("error in mother register");
					}
					sitterdao.insertSitter(sitter);	
					}	
				
			
		
			
			
			@RequestMapping(value = "apply2.action", method = RequestMethod.POST)
			@ResponseBody
				public void applySitterPost2(@RequestBody Age age, 						
											HttpServletResponse resp, BindingResult result) {
										
					if(result.hasErrors()){
						System.out.println("error in mother register");
					}
					sitterdao.insertAgeGroup(age);
					}	
		
			
			
		
			@RequestMapping(value = "apply3.action", method = RequestMethod.POST)
			@ResponseBody
				public void applySitterPost3(@RequestBody Skill skill, 						
											HttpServletResponse resp, BindingResult result) {										
					if(result.hasErrors()){
						System.out.println("error in mother register");
					}
					sitterdao.insertSkills(skill);
					}	

	
	
}

