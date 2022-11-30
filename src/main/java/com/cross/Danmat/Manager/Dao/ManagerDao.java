package com.cross.Danmat.Manager.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.Manager.data.ManagerRowMapper;
import com.cross.Danmat.Manager.data.UserRowMapper;
import com.cross.Danmat.Manager.domain.Manager;
import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.board.domain.Board;

public class ManagerDao {
	private JdbcTemplate jdbcTemplate;

	public ManagerDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//아이디로 회원 찾기
	public List<UserCommand> SearchEmailForm(String email){
		String sql ="SELECT * FROM User WHERE email like ? ";
		return jdbcTemplate.query(sql, new UserRowMapper(), "%" +email+"%");
	}
	
	public List<UserCommand> SearchIdForm(String userid){
		String sql ="SELECT * FROM User WHERE userid like ? ";
		return jdbcTemplate.query(sql, new UserRowMapper(), "%"+ userid+ "%");
	}
	
	//모든 유저 목록
	public List<UserCommand> getAllUserForm(){
		String sql ="SELECT * FROM User";
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
	
	//관리자 유효성 검사용
	public List<Manager> isManager() {
		String sql = "SELECT * FROM Manager";
		
		return jdbcTemplate.query(sql, new ManagerRowMapper());
	}
	
	//유저 삭제
	public void DeleteUser(String userid) {
		String sql = "DELETE FROM user WHERE userid = ?";
		jdbcTemplate.update(sql, userid);
	}
	
	//신규 유저 목록
	public List<UserCommand> getNewUserCount(String date) {
		String sql = "SELECT * FROM user WHERE regdate Like ?";
		return jdbcTemplate.query(sql, new UserRowMapper(), "%"+date+"%");
	}
	
	//새글 목록
		public List<Board> newboardList(LocalDate date) {
			String sql = "select * from BOARD where createDate Like ? order by board_idx desc";
			return jdbcTemplate.query(sql, new RowMapper<Board>() {

				@Override
				public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
					Board board = new Board(rs.getInt("board_idx"),rs.getString("notice"), rs.getString("userId"), rs.getString("title"),
							rs.getString("content"), rs.getDate("createDate"), rs.getDate("updateDate"),
							rs.getDate("deleteDate"), rs.getString("delete_yn"), rs.getLong("readCount"),
							rs.getLong("replyCount"));
					return board;
				}
			}, "%"+date+"%");
		}
		
		public List<Board> boardList() {
			String sql = "select * from BOARD order by board_idx desc";
			return jdbcTemplate.query(sql, new RowMapper<Board>() {

				@Override
				public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
					Board board = new Board(rs.getInt("board_idx"),rs.getString("notice"), rs.getString("userId"), rs.getString("title"),
							rs.getString("content"), rs.getDate("createDate"), rs.getDate("updateDate"),
							rs.getDate("deleteDate"), rs.getString("delete_yn"), rs.getLong("readCount"),
							rs.getLong("replyCount"));
					return board;
				}
			});
		}
	
		//게시글 삭제
		public void DeleteBoard(String board_idx) {
			String sql = "DELETE FROM Board WHERE board_idx = ?";
			jdbcTemplate.update(sql, board_idx);
		}
}
