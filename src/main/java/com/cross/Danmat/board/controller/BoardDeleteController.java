package com.cross.Danmat.board.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.board.domain.Board;
import com.cross.Danmat.board.service.BoardService;

@Controller
@RequestMapping("board/boardDelete")
public class BoardDeleteController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	BoardService boardService = context.getBean("boardService", BoardService.class);
	
	
	@GetMapping
	public String boardDelete(Board board) {
		boardService.boardDelete(board);
		context.close();
		return "redirect:/board";
	}
}
