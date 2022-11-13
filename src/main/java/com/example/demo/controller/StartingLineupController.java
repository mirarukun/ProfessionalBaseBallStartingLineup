package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartingLineupController {
//	@Autowired
//	StartingLineupService startingLineupService;
	
	//スタメン登録画面表示処理
	@GetMapping("/newStartingLineup")
	public ModelAndView newStartingLineup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newStartingLineup");
		return mav;
	}
}
