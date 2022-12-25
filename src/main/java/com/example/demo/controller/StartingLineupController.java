package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.StartingLineup;
import com.example.demo.service.StartingLineupService;

@Controller
public class StartingLineupController {
	@Autowired
	StartingLineupService startingLineupService;
	
	//スタメン登録画面表示処理
	@GetMapping("/newStartingLineup")
	public ModelAndView newStartingLineup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("newStartingLineup");
		return mav;
	}
	
	//スタメン登録処理
	@PostMapping("/addStartingLineup")
	public ModelAndView addStartingLineup(@ModelAttribute("startingLineupModel") StartingLineup startingLineup){
		//スタメンをテーブルに格納
		startingLineupService.saveStartingLineup(startingLineup);

		// TOP画面へリダイレクト
		return new ModelAndView("redirect:/");

	}
}
