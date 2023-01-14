package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;

@Controller
public class PlayerController {
	@Autowired
	PlayerService playerService;
	
	//選手登録画面表示処理
	@GetMapping("/playerRegister")
	public ModelAndView playerRegister(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("playerRegister");
		Player player = new Player();
		mav.addObject("playerModel", player);
		model.addAttribute("subPositionCheckBox",getCheckBoxSubPosition());
		return mav;
	}
		
	//サブポジションのチェックボックス用
	private Map<String ,String> getCheckBoxSubPosition(){
		Map<String, String> checkBoxSubPosition = new LinkedHashMap<String , String>();
		checkBoxSubPosition.put("先発投手", "投手（先発）");
		checkBoxSubPosition.put("中継ぎ投手", "投手（中継ぎ)");
		checkBoxSubPosition.put("抑え投手", "投手（抑え）");
		checkBoxSubPosition.put("捕手", "捕手");
		checkBoxSubPosition.put("一塁手", "一塁手");
		checkBoxSubPosition.put("二塁手", "二塁手");
		checkBoxSubPosition.put("三塁手", "三塁手");
		checkBoxSubPosition.put("遊撃手", "遊撃手");
		checkBoxSubPosition.put("左翼手", "左翼手");
		checkBoxSubPosition.put("中堅手", "中堅手");
		checkBoxSubPosition.put("右翼手", "右翼手");
		checkBoxSubPosition.put("なし", "サブポジションはなし");
		return checkBoxSubPosition;
	}
		
		//選手登録処理
		@PostMapping("/addPlayer")
		public ModelAndView addPlayer(@ModelAttribute("playerModel") Player player){
			//選手情報をテーブルに格納
			playerService.savePlayer(player);

			// TOP画面へリダイレクト
			return new ModelAndView("redirect:/");
			
		}
		
		// 登録選手表示画面
		@GetMapping("/playerDisplay")
		public ModelAndView playerDisplay() {
		ModelAndView mav = new ModelAndView();
		// 登録済選手を全件取得
		List<Player> players = playerService.findAllPlayer();
		// 画面遷移先を指定
		mav.setViewName("/playerDisplay");
		// 投稿データオブジェクトを保管
		mav.addObject("Players", players);
		
		return mav;
		}
}
