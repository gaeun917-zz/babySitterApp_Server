package com.nhserver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhserver.model.dao.nhManboDao;
import com.nhserver.model.dto.Manbo;

@Controller
@RequestMapping(value = "/nhmanbo/")
public class nhManboController {

	@Autowired
	@Qualifier("oraclenhManboDao")
	private nhManboDao dao;

	public void setManboDao(nhManboDao nhmanboDao) {
		this.dao = nhmanboDao;
	}

	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public void register(@RequestBody Manbo manbo, HttpServletRequest req, HttpServletResponse resp) {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		PrintWriter writer;
		if (manbo != null) {
			try {
				writer = resp.getWriter();
				req.setCharacterEncoding("utf-8");
				resp.setContentType("text/plain;charset=utf-8");

				String json = gson.toJson(manbo);
				writer.println(json);

				dao.insert(manbo);

				System.out.println(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@RequestMapping(value = "regdate.action", method = RequestMethod.GET)
	public void login(String memberId, HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();

			if (memberId == null || memberId.length() == 0) {
				writer.println("failed");
			} else {
				Manbo manbo = dao.getRegDateByMemberId(memberId);
				String json = null;
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

				if (manbo != null) {
					// 로그인 성공 보내기
					
					json = gson.toJson(manbo);
//					System.out.println(json);
					writer.println(json);
				} else {
					// 로그인 실패 보내기
					writer.println("failed");
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
