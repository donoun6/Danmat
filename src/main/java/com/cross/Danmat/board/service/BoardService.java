package com.cross.Danmat.board.service;
import java.util.List;

import com.cross.Danmat.board.domain.Board;

public interface BoardService {
	public List<Board> boardList(); // 게시판 목록
	public List<Board> noticeBoardList(); // 공지 목록
	public List<Board> SearchboardByTitle(String title); // 제목 검색
	public List<Board> SearchboardByUserId(String UserId); // id 검색
	public void boardCreate(Board board); // 게시글 쓰기
	public Board boardDetail(int board_idx); // 게시글 상세 보기 
	public void boardUpdate(Board board); // 게시글 수정
	public void boardDelete(Board board); // 게시글 삭제
	public void readCount(int board_idx); // 조회수
}