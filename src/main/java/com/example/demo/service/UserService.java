package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	// レコード追加
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	// レコード１件取得（ログイン処理。メールアドレスをキーとしている）
		public User select(String email) {
			return userRepository.findByEmail(email);
		}

}
