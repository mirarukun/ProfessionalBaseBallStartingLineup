package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.IineService;

@Controller
public class IineController {
	@Autowired
	private IineService iineService;
	
    // いいね数を増加させるエンドポイント
    @PostMapping("/increment/{iineId}")
    public ResponseEntity<String> incrementIine(@PathVariable int iineId) {
        int updatedCount = iineService.incrementIineCount(iineId);
        return ResponseEntity.ok(String.valueOf(updatedCount));
    }

    // 現在のいいね数を取得するエンドポイント
//    @GetMapping("/count/{iineId}")
//    public ResponseEntity<String> getIineCount(@PathVariable int iineId) {
//        int count = iineService.getIineById(iineId).getIineCount();
//        return ResponseEntity.ok(String.valueOf(count));
//    }
    
 // 現在のいいね数を取得するエンドポイント
    @GetMapping("/count/{iineId}")
    public ResponseEntity<Map<String, Integer>> getIineCount(@PathVariable int iineId) {
        int count = iineService.getIineById(iineId).getIineCount();
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }
}