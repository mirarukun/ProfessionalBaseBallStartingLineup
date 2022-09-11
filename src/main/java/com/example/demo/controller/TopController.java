package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;

@Controller
public class TopController {
	
	//セッションに関するAutowired
	@Autowired
	HttpSession session;
		
	@GetMapping
	public ModelAndView top() {
		ModelAndView mav = new ModelAndView();
		
		// session領域のloginUserの情報をgetAttributeし、User型のuserに格納
		User user = (User) session.getAttribute("loginUser");
		
		mav.addObject("loginUser", user);
		mav.setViewName("top");
		return mav;
	}

}
