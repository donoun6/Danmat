package com.cross.Danmat.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.board.BoardRowMapper;
import com.cross.Danmat.board.domain.Board;

public class BoardDao {
	private JdbcTemplate jdbcTemplate;

	public BoardDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public BoardDao() {
		super();
	}
	
	// 게시판 목록
	public List<Board> boardList() {
		String sql = "select * from board where delete_yn='n' and notice='N' order by board_idx desc";
		return jdbcTemplate.query(sql,  new BoardRowMapper());
	}
	
	// 공지 목록
	public List<Board> noticeBoardList() {
		String sql = "select * from board where delete_yn='n' and notice='Y' order by board_idx desc limit 3";
		return jdbcTemplate.query(sql,  new BoardRowMapper());
	}
		
	// 게시물 작성
	public void boardCreate(Board board) {
		String sql = "INSERT INTO Board(notice, userId, title, content) VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, board.getNotice(), board.getUserId(), board.getTitle(), board.getContent());		
	}
	 
	// 게시글 상세보기
	public Board boardDetail(int board_idx) {
		String sql = "select * from board where board_idx= ?";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board(rs.getInt("board_idx"), rs.getNString("notice"), rs.getString("userId"), rs.getString("title"),				
						rs.getString("content"), rs.getDate("createDate"), rs.getLong("readCount"),
						rs.getLong("replyCount"));
				return board;
			}
		}, board_idx);
	}
	
	// 게시글 검색
	// 제목으로 검색
	public List<Board> SearchBoardByTitle(String title){
		String sql = "select * from board where title like ?";
		return jdbcTemplate.query(sql, new BoardRowMapper(),"%"+ title+"%");
//		return jdbcTemplate.query(sql, new RowMapper<Board>() {
//			@Override
//			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Board board = new Board(rs.getInt("board_idx"), rs.getNString("notice"), rs.getString("userId"), rs.getString("title"),				
//						rs.getString("content"), rs.getDate("createDate"), rs.getLong("readCount"),
//						rs.getLong("replyCount"));
//				return board;
//			}
//		}, "%"+title+"%");
	}
	
	public List<Board> SearchBoardByUserId(String userId) {
		String sql = "select * from board where userId = ?";
		return jdbcTemplate.query(sql, new BoardRowMapper(), userId);
	}
	
	// 게시글 수정
	public void boardUpdate(Board board) {
		String sql = "UPDATE Board SET title=?, content=?, updateDate=now() where board_idx=?";		
		jdbcTemplate.update(sql, board.getTitle(), board.getContent(), board.getBoard_idx());
	}
	
	// 게시글 삭제
	public void boardDelete(Board board) {
		String sql = "UPDATE Board SET delete_yn='Y' WHERE board_idx=?";
		jdbcTemplate.update(sql, board.getBoard_idx());
	}
	
	//조회수 증가
	public void readCount(int board_idx) {
		String sql = "UPDATE Board SET readCount=readCount+1 WHERE board_idx=?";
		jdbcTemplate.update(sql, board_idx);
	}
}