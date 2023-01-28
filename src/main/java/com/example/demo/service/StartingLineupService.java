package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StartingLineup;
import com.example.demo.repository.StartingLineupRepository;

@Service
public class StartingLineupService {

	@Autowired
	StartingLineupRepository startingLineupRepository;

	// レコード追加
	public void saveStartingLineup(StartingLineup startingLineup) {
		startingLineupRepository.save(startingLineup);
	}
	
	//登録されているスタメン情報を取得
	public List<StartingLineup> findAllStartingLineup() {
		return startingLineupRepository.findAll();
	}

}
