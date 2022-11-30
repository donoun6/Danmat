package com.cross.Danmat.board.service;

import java.util.List;

import com.cross.Danmat.board.dao.BoardDao;
import com.cross.Danmat.board.domain.Board;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = null;

	public BoardServiceImpl() {
		super();
	}

	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	// 게시판 목록
	@Override
	public List<Board> boardList() {
		return boardDao.boardList();
	}
	
	// 공지 게시판 목록
	@Override
	public List<Board> noticeBoardList() {
		return boardDao.noticeBoardList();
	}
		
	// 게시물 작성
	@Override
	public void boardCreate(Board board) {
		boardDao.boardCreate(board);
	}

	// 게시물 상세내용 불러오기
	@Override
	public Board boardDetail(int board_idx) {
		readCount(board_idx);
		return boardDao.boardDetail(board_idx);
	}

	// 게시물 수정
	@Override
	public void boardUpdate(Board board) {
		boardDao.boardUpdate(board);
	}

	// 게시물 삭제
	@Override
	public void boardDelete(Board board) {
		boardDao.boardDelete(board);	
	}

	// 조회수
	@Override
	public void readCount(int board_idx) {
		boardDao.readCount(board_idx);		
	}
	
	// 제목 검색
	@Override
	public List<Board> SearchboardByTitle(String title) {
		return boardDao.SearchBoardByTitle(title);
	}

	// id 검색
	@Override
	public List<Board> SearchboardByUserId(String userId) {
		return boardDao.SearchBoardByUserId(userId);
	}
	
	
}