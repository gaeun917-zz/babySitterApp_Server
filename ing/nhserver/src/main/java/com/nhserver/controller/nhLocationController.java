package com.nhserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhserver.model.dao.nhLocationDao;
import com.nhserver.model.dto.Location;

@Controller
@RequestMapping(value = "/nhLocation/")
public class nhLocationController {

	@Autowired
	@Qualifier("oraclenhLocationDao")
	private nhLocationDao dao;

	public void setLocationDao(nhLocationDao nhlocationDao) {
		this.dao = nhlocationDao;
	}

	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public void register(@RequestBody Location nhLocation, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("location.action!!!!!!!!!!!!!!!!!!!!1");
		if (nhLocation != null) {
			try {
				dao.insert(nhLocation);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
