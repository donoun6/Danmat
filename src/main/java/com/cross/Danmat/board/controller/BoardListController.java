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

@Controller
@RequestMapping("board")
public class BoardListController {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		BoardServiceImpl boardService = context.getBean("boardService", BoardServiceImpl.class);
				
		@GetMapping
	      public String BoardListForm(Model model) {
	         List<Board> boardList = boardService.boardList();
	         List<Board> noticeBoardList = boardService.noticeBoardList();
	         String page_input = "";
	         int page_size = (boardList.size()-1)/8 + 1;
	         
	         for(int i = 0; i < page_size; i++) {
	            page_input += "<li><input type=submit name=page value="+(i+1)+"></li>";
	         }
	         int page = 0;
	         model.addAttribute("boardList", boardList);
	         model.addAttribute("page" , page);
	         model.addAttribute("page_num", page_input);
	         model.addAttribute("notice_list", noticeBoardList);
	         context.close();
	         return "board/BoardList";
	      }

		@PostMapping
	      public String BoardListForm(Model model, @RequestParam("page")int page) {
	         List<Board> boardList = boardService.boardList();
	         List<Board> noticeBoardList = boardService.noticeBoardList();
	         page = (page-1) * 10;
	         String page_input = "";
	         int page_size = (boardList.size()-1)/8 + 1;
	         for(int i = 0; i < page_size; i++) {
	            page_input += "<li><input type=submit name=page value="+(i+1)+"></li>";
	         }
	         model.addAttribute("boardList", boardList);
	         model.addAttribute("page" , page);
	         model.addAttribute("page_num", page_input);
	         model.addAttribute("notice_list",noticeBoardList);
	         System.out.println(boardList.size());
	         context.close();
	         return "board/BoardList";
	      }
	
		
}
