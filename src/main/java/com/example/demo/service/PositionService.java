package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Position;
import com.example.demo.repository.PositionRepository;

@Service
public class PositionService {
	
	@Autowired
	PositionRepository positionRepository;
	
	public List<Position> findAllPosition() {
		//全登録されている全ての選手を取得
		return positionRepository.findAll();
	}

}
