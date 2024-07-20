package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Iine;
import com.example.demo.entity.Player;
import com.example.demo.entity.Position;
import com.example.demo.entity.StartingLineup;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.IineService;
import com.example.demo.service.PlayerService;
import com.example.demo.service.PositionService;
import com.example.demo.service.StartingLineupService;
import com.example.demo.service.UserService;

@Controller
public class TopController {
	
	//セッションに関するAutowired
	@Autowired
	HttpSession session;
	
	@Autowired
	StartingLineupService startingLineupService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	PositionService positionService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	IineService iineService;
	
	//変数定義
	User startingLineupUser;
	Player onePlayer;
	Position onePosition;
	Player twoPlayer;
	Position twoPosition;
	Player threePlayer;
	Position threePosition;
	Player fourPlayer;
	Position fourPosition;
	Player fivePlayer;
	Position fivePosition;
	Player sixPlayer;
	Position sixPosition;
	Player sevenPlayer;
	Position sevenPosition;
	Player eightPlayer;
	Position eightPosition;
	Player ninePlayer;
	Position ninePosition;
	Player starterPitcherPlayer;
	Player setUpperPitcherPlayer;
	Player closerPitcherPlayer;
	
	//トップ画面表示処理
	@GetMapping
	public ModelAndView top() {
		ModelAndView mav = new ModelAndView();
		
		// session領域のloginUserの情報をgetAttributeし、User型のuserに格納
		User user = (User) session.getAttribute("loginUser");
		
		// スタメン表示
		// 登録済のスタメンを全件取得
		List<StartingLineup> StartingLineups = startingLineupService.findAllStartingLineup();
		
		// 登録済選手を全件取得
		List<Player> players = playerService.findAllPlayer();
		
		// 登録済選手を全件取得
		List<Position> positions = positionService.findAllPosition();
		
		// ユーザー情報を全件取得
		List<User> users = userService.findAllUser();
		
		// コメントを全件取得
		List<Comment> contentData = commentService.findAllComment();
		
		// 各スタメンに対応するいいね数を取得
		List<Iine> iines = iineService.getAllIines();
		
	    // 第一引数にスタメンID、第二引数にいいね数を入れる予定
        Map<Integer, Integer> iineMap = new HashMap<>();
        
        // リストの各要素についてループを回します
        for (Iine iine : iines) {
            // StartingLineupIdをキーに、IineCountをバリューとしてマップに追加します
            iineMap.put(iine.getStartingLineupId(), iine.getIineCount());
        }
		
		mav.addObject("loginUser", user);
		mav.addObject("StartingLineups", StartingLineups);
		mav.addObject("players", players);
		mav.addObject("positions", positions);
		mav.addObject("users", users);
		mav.addObject("contents", contentData);
		mav.addObject("iineMap", iineMap);
		mav.setViewName("top");
		return mav;
	}

}
