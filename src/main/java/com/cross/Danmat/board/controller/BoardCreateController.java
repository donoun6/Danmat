package com.cross.Danmat.board.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.board.domain.Board;
import com.cross.Danmat.board.service.BoardServiceImpl;

@Controller
@RequestMapping("board/boardCreate")
public class BoardCreateController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	BoardServiceImpl boardService = context.getBean("boardService", BoardServiceImpl.class);

	@GetMapping
	public String boardCreate(Model model) {
		model.addAttribute("board", new Board());
		return "/board/BoardCreate";
	}
	
	@PostMapping
	public String boardCreate(@ModelAttribute("board")
	Board board, Model model) {
//		System.out.println(board.getNotice());
		if(board.getNotice() == null) {	// 관리자 로그인 후 공지등록 버튼이 체크된 경우에만 공지로 등록되도록
			board.setNotice("N");		// 공지등록 버튼이 체크 안된 경우 Notice 칼럼값 N
		}
		boardService.boardCreate(board);
		model.addAttribute("boardList", boardService.boardList());
		context.close();
		return "redirect:/board";
	}
}
