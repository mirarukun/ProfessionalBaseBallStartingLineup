package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

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
		
		//エラーメッセージ
		List<String> errorMessages = new ArrayList<String>();
		isValidUserAdd(user, errorMessages);
		if (errorMessages.size() > 0) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/newUser");
			mav.addObject("userModel", user);
			mav.addObject("errorMessages", errorMessages);
			return mav;
		}
		
		
		//ユーザ情報をテーブルに格納
		userService.saveUser(user);
		
		session.setAttribute("loginUser", user);//←セッションを張っている

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
		ModelAndView mav = new ModelAndView();
		
		List<String> errorMessages = new ArrayList<String>();
		String email = user.getEmail();
		String password = user.getPassword();

		//ログインのバリデーションチェック
		isValidLogin(email, password, errorMessages);
		if (errorMessages.size() > 0) {
			mav.setViewName("/login");
			mav.addObject("userModel", user);
			mav.addObject("errorMessages", errorMessages);
			return mav;
		}
		
		User loginUser = userService.select(email);
		
		if (loginUser == null || !(loginUser.getPassword().equals(password))) {
			mav.setViewName("/login");
			return mav;
		}
		
		session.setAttribute("loginUser", loginUser); //←セッションを張っている
		
		return new ModelAndView("redirect:/");
	}

	//ユーザー編集画面表示
	@GetMapping("/editUser")
	public ModelAndView editUser() {
		ModelAndView mav = new ModelAndView();
		
		//フォームに今の情報入れておく
		User sessionUser = (User) session.getAttribute("loginUser");
		User loginUser = userService.select(sessionUser.getEmail());
		mav.addObject("userModel", loginUser);
		
		mav.setViewName("editUser");
		return mav;
	}
	
	//ユーザ情報編集処理
	@PostMapping("/editUser")
	public ModelAndView editUser(@ModelAttribute("loginUser") User user) {
		ModelAndView mav = new ModelAndView();
		List<String> errorMessages = new ArrayList<String>();
		
		User sessionUser = (User) session.getAttribute("loginUser");
		
		//バリデーションチェック
		isValidUserEdit(user, errorMessages);
		if (errorMessages.size() > 0) {
			mav.setViewName("/editUser");
			mav.addObject("userModel", user);
			mav.addObject("errorMessages", errorMessages);
			return mav;
		}
		
		//ユーザ情報をテーブルに格納
		user.setId(sessionUser.getId());
		userService.updateUser(user);
		
		//更新したユーザー情報をloginUserに格納
		User loginUser = userService.select(sessionUser.getEmail());
		mav.addObject("userModel", loginUser);

		mav.setViewName("/top");
		return mav;
	}
	
	// ログアウト処理
	@GetMapping("/logout")
	public ModelAndView logout() {
		// セッションクリア
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	//ユーザ登録のバリデーションチェック
	private void isValidUserAdd(User user, List<String> errorMessages) {
		String nickname = user.getNickname();
		String email = user.getEmail();
		String password = user.getPassword();
		
		//アカウント名チェック
		if (Strings.isBlank(nickname)) {
			errorMessages.add("アカウント名を入力してください");
		} else if(nickname.length() >= 11) {
			errorMessages.add("アカウント名は10文字以下で入力してください");
		}
		
		//Emailチェック
		User loginUser = userService.select(email);
		if (StringUtils.isEmpty(email)) {
			errorMessages.add("メールアドレスを入力してください");
		} else if(email.length() > 50) {
			errorMessages.add("メールアドレスは50文字以下で入力してください");
		} else if(!(loginUser == null)) {
			errorMessages.add("すでに存在するメールアドレスです");
		}
		
		//パスワードチェック
		if (Strings.isBlank(password)) {
			errorMessages.add("パスワードを入力してください");
		} else if(password.length() < 6) {
			errorMessages.add("パスワードは6文字以上で入力してください");
		}
	}
	
	//ユーザログインのバリデーションチェック
	private void isValidLogin(String email, String password, List<String> errorMessages) {
		//Emailチェック
		if (StringUtils.isEmpty(email)) {
			errorMessages.add("メールアドレスを入力してください");
		}

		//パスワードチェック
		if (Strings.isBlank(password)) {
			errorMessages.add("パスワードを入力してください");
		}
	}
	
	
	//ユーザ編集のバリデーションチェック
	private void isValidUserEdit(User user, List<String> errorMessages) {
		//↓ログインユーザ情報
		User loginUser = (User) session.getAttribute("loginUser");

		//↓入力した値
		String name = user.getNickname();
		String email = user.getEmail();

		//アカウント名チェック
		if (Strings.isBlank(name)) {
			errorMessages.add("アカウント名を入力してください");
		} else if(name.length() >= 11) {
			errorMessages.add("アカウント名は10文字以下で入力してください");
		}

		//Emailチェック
		User emailCheck = userService.select(email);
		if (StringUtils.isEmpty(email)) {
			errorMessages.add("メールアドレスを入力してください");
		} else if(email.length() > 50) {
			errorMessages.add("メールアドレスは50文字以下で入力してください");
		} else if(emailCheck != null) {
			if(email.equals(emailCheck.getEmail()) && !(loginUser.getId() == emailCheck.getId())) {
				errorMessages.add("すでに存在するメールアドレスです");
			}
		}

		if(StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(loginUser.getPassword());
		}else {
			String pass = user.getPassword();
			//パスワードチェック
			if(pass.length() < 6) {
				errorMessages.add("パスワードは6文字以上で入力してください");
			} 
		}
	}
}