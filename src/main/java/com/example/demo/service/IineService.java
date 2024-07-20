package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Iine;
import com.example.demo.repository.IineRepository;

@Service
public class IineService {
    @Autowired
    private IineRepository iineRepository;

    // いいね数を増加させる処理
    @Transactional
    public int incrementIineCount(int iineId) {
        // 指定されたIDのいいねをデータベースから取得する
        // 見つからなかった場合は例外をスローする
        Iine iine = iineRepository.findById(iineId).orElseThrow(InvalidIineIdException::new);
        
        // 現在のいいね数に1を加える
        int updatedCount = iine.getIineCount() + 1;
        
        // 更新されたいいね数をセットする
        iine.setIineCount(updatedCount);
        
        // 変更をデータベースに保存する
        iineRepository.save(iine);
        
        // 更新後のいいね数を返す
        return updatedCount;
    }
    
    // すべての「いいね」の情報を取得する処理(TOP画面表示用)
    public List<Iine> getAllIines() {
        return iineRepository.findAll();
    }

    // 指定されたIDの「いいね」の情報を取得する処理（iineIdがDBにない場合は例外をスローする）
    public Iine getIineById(int iineId) {
        return iineRepository.findById(iineId).orElseThrow(InvalidIineIdException::new);
    }

    
    // IDが無効な場合にスローされる例外
    private static class InvalidIineIdException extends RuntimeException {
        public InvalidIineIdException() {
            // 例外メッセージを設定する
            super("無効な「いいね」IDです");
        }
    }
}
