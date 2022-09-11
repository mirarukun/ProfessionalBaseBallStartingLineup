package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;

	//新規登録画面表示処理
	@GetMapping("/newUser")
	public ModelAndView newUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newUser");
		return mav;
	}
	
	//ユーザ登録処理
	@PostMapping("/addUser")
	public ModelAndView addUser(@ModelAttribute("userModel") User user){
		//ユーザ情報をテーブルに格納
		userService.saveUser(user);

		// TOP画面へリダイレクト
		return new ModelAndView("redirect:/");
	}
		
	//ログイン画面表示処理
	@GetMapping("/login")
	public ModelAndView login() {
	ModelAndView mav = new ModelAndView();
	mav.setViewName("login");
	return mav;
	}
	
	// ログイン処理
	@PostMapping("/postLogin")
	public ModelAndView login(@ModelAttribute("loginForm") User user) {	
		String email = user.getEmail();
		
		User loginUser = userService.select(email);
		
		session.setAttribute("loginUser", loginUser); //←セッションを張っている
		
		return new ModelAndView("redirect:/");
	}

	//ユーザー編集画面表示
	@GetMapping("/editUser")
	public ModelAndView editUser() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editUser");
		return mav;
	}
	
	// ログアウト処理
	@GetMapping("/logout")
	public ModelAndView logout() {
		// セッションクリア
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
