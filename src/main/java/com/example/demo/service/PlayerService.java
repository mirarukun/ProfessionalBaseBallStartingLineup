package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;

@Service
public class PlayerService {
	@Autowired
	PlayerRepository playerRepository;

	// レコード追加
	public void savePlayer(Player player) {
		
     //サブポジションを配列から文字列に修正
	String str = String.join(",", player.getArraySubPosition());
	player.setSubPosition(str);
	
	playerRepository.save(player);
	}
}
