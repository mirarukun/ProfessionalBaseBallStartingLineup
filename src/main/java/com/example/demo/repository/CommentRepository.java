package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	//最新コメント１件取得
	 @Query(nativeQuery = true, value = "select * from comments order by id desc limit 1 ")
	Comment findlatest();
	 
	//SQL 補足
	//SQLで抽出したデータを並べ替える（ソートを行う）場合はORDER BY句を使用します。
	//コメント（comments）テーブルを、idの降順（desc）に並べ変えて、1件取得している。（つまり最新コメントを取得している）
	 
}