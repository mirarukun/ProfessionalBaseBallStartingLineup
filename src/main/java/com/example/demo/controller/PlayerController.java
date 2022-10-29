package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.PlayerService;

@Controller
public class PlayerController {
	@Autowired
	PlayerService playerService;
	
	//選手登録画面表示処理
		@GetMapping("/playerRegister")
		public ModelAndView playerRegister() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("playerRegister");
			return mav;
		}
		
	
}
