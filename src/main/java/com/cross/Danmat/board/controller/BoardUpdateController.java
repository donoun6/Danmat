package com.cross.Danmat.board.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.board.domain.Board;
import com.cross.Danmat.board.service.BoardServiceImpl;

@Controller
@RequestMapping("board/boardUpdate")
public class BoardUpdateController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	BoardServiceImpl boardService = context.getBean("boardService", BoardServiceImpl.class);
			
	@GetMapping
	public String boardUpdateForm(Model model, int board_idx) {
		model.addAttribute("board", boardService.boardDetail(board_idx));
		return "/board/BoardUpdate";
	}
	
	@PostMapping
	public String boardUpdate(Board board) {
		boardService.boardUpdate(board);
		context.close();
		return "redirect:/board";
	}
}
