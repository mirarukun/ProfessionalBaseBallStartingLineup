package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	//ログイン画面表示処理
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	//新規登録画面表示処理
	@GetMapping("/newUser")
	public ModelAndView newUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newUser");
		return mav;
	}

	//ユーザー編集画面表示
	@GetMapping("/editUser")
	public ModelAndView editUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editUser");
		return mav;
	}
}
