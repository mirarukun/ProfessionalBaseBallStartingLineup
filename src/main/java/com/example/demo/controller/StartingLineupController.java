package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Player;
import com.example.demo.entity.Position;
import com.example.demo.entity.StartingLineup;
import com.example.demo.entity.User;
import com.example.demo.service.PlayerService;
import com.example.demo.service.PositionService;
import com.example.demo.service.StartingLineupService; 

@Controller
public class StartingLineupController {
	@Autowired
	StartingLineupService startingLineupService;
	@Autowired
	PlayerService playerService;
	
	@Autowired
	PositionService positionService;
	
	//セッションに関するAutowired
	@Autowired
	HttpSession session;
	
	//スタメン登録画面表示処理
	@GetMapping("/newStartingLineup")
	public ModelAndView newStartingLineup(Model model) {
		
		ModelAndView mav = new ModelAndView();
		
		// Playerを全件取得
		List<Player> Players = playerService.findAllPlayer();
		
		// Positionを全件取得
		List<Position> Positions = positionService.findAllPosition();
		
		mav.addObject("Players", Players);
		mav.addObject("Positions", Positions);
		
		// 画面遷移先を指定
		mav.setViewName("newStartingLineup");
		return mav;
	}
	
	
	//スタメン登録処理
	@PostMapping("/addStartingLineup")
	public ModelAndView addStartingLineup(@ModelAttribute("startingLineupModel") StartingLineup startingLineup){
		// session領域のloginUserの情報をgetAttributeし、User型のuserに格納
		User user = (User) session.getAttribute("loginUser");
		
		//ユーザーIDをスターティングラインナップモデルに格納
		startingLineup.setUserId(user.getId());
		
		//スタメンをテーブルに格納
		startingLineupService.saveStartingLineup(startingLineup);

		// TOP画面へリダイレクト
		return new ModelAndView("redirect:/");

	}
}
