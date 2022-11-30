package com.cross.Danmat.board.controller;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.board.domain.Board;
import com.cross.Danmat.board.service.BoardServiceImpl;

@Controller("Board.Controller.BoardSearchController")
@RequestMapping("searchBoard")
public class BoardSearchController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	BoardServiceImpl boardService = context.getBean("boardService", BoardServiceImpl.class);

	@GetMapping // 게시판 검색
	public String BoardSearchForm(Model model) {
		List<Board> boardList = boardService.boardList();
		model.addAttribute("boardList", boardList);
		return "board/BoardList";
	}
	
	@PostMapping
	public String searchTitle(@RequestParam("type")String type,
			@RequestParam("Name") String name, Model model) {
		String page_input = "";
//		System.out.println(name);
		if(type.equals("title")) {
			List<Board> searchTitle = boardService.SearchboardByTitle(name);
			model.addAttribute("boardList", searchTitle);
			int page_size = (searchTitle.size() - 1)/8 + 1;
	        for(int i = 0; i < page_size; i++) {
	           page_input += "<li><input type=submit name=page value="+(i+1)+"></li>";
	        }
		}else if(type.equals("userId")) {
			List<Board> searchId = boardService.SearchboardByUserId(name);
			model.addAttribute("boardList", searchId);
			int page_size = (searchId.size() - 1)/8 + 1;
	        for(int i = 0; i < page_size; i++) {
	           page_input += "<li><input type=submit name=page value="+(i+1)+"></li>";
	        }
		}
			return "board/BoardList";
	}
}
