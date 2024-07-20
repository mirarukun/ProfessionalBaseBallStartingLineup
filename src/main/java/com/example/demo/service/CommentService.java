package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;


@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;

	// レコード追加
	public void saveComment(Comment comment) {
		commentRepository.save(comment);
	}

	//DBに登録されている全てのコメントを取得（全件取得）
	public List<Comment> findAllComment() {
		return commentRepository.findAll();
	}
	
	// コメント1件取得 （最新データ）
	public Comment findSelectedPost() {
		return commentRepository.findlatest();
	}
}
