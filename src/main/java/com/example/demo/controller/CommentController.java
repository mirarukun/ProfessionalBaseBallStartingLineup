package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	// コメントを追加するメソッド
	//@ResponseBodyはアノテーションを付けることで戻り値をコンテンツとして反映させることができます。
	//これは、JSON や XML などを返す場合に使用されることが多いです。
	@PostMapping("/addComment")
	@ResponseBody
	public String addComment(@RequestBody Comment comment) {
	//public String addComment(@RequestParam("text") String text, @RequestParam("StartingLineupId") int startingLineupId) {
		// コメントをテーブルに格納
		commentService.saveComment(comment);
		// コメントを取得
		Comment LatestComment = commentService.findSelectedPost();
		
		//return "success";
		return GetJson(LatestComment);
	}
	
	//private String GetJsonのメソッドで戻り値の型をJSON型に変形させます。
    private String GetJson(Comment LatestComment){
    	
    	//戻り値
        String retVal = null;
        //オブジェクト・マッパー
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(LatestComment);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return retVal;
    }
}