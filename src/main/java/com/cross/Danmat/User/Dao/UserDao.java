package com.cross.Danmat.User.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.User.Command.UserCommand;



public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	//============================================ JDBC data ================================================
	public UserDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//============================================ 회원가입 추가 =================================================
	public void addUser(UserCommand menu) {
		String sql = "INSERT INTO User (userid, passwd, email) VALUES (?,?,?)";
		jdbcTemplate.update(sql,menu.getUserid(),menu.getPasswd(),menu.getEmail());
		System.out.println("회원 정보 등록");
	}
	
	//============================================ ID중복 검사===========================================
	public int checkingId(String userid) {
		try {
			String sql = "SELECT userid FROM User WHERE userid = ?";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
				
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString(1).contentEquals(userid)) {
						System.out.println("아이디 중복");
						return 1;
					}
					return null;
				}
				
			},userid);
		} catch (IncorrectResultSizeDataAccessException error) { // 쿼리문에 해당하는 결과가 없거나 2개 이상일 때 예외처리
			System.out.println("아이디 중복 없음"); //queryForObject는 쿼리문에 해당하는 입력값이 이상하면 예외를 발생시킴 , query는 예외를 발생시키지는 않지만 list형식으로 결과를 출력함
		    return 2;
		}
	}
	
	//============================================ E-mail중복 검사===========================================
	public int checkingEmail(String email) {
		try {
			String sql = "SELECT email FROM User WHERE email = ?";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
				
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString(1).contentEquals(email)) {
						System.out.println("이메일 중복");
						return 1;
					}
					return null;
				}
				
			},email);
		} catch (IncorrectResultSizeDataAccessException error) {
			System.out.println("이메일 중복 없음");
		    return 2;
		}
	}
	
	//============================================ 로그인 검사 ================================================
	public int login(String userid,String passwd) {
		try {
			String sql = "SELECT passwd FROM User WHERE userid = ? ";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString(1).contentEquals(passwd)) {
						System.out.println("로그인");
						return 1;
					}
					return null;
				}
			},userid); 
		} catch (IncorrectResultSizeDataAccessException error) {
			System.out.println("잘못된 정보");
			return 2;
		} catch (NullPointerException error) {
			System.out.println("잘못된 정보");
			return 2;
		} 
		
	}
	
	//============================================ 아이디 찾기 ================================================
	public List<UserCommand> findId(String email){
		String sql ="SELECT userid FROM User WHERE email = ? ";
		return jdbcTemplate.query(sql, new RowMapper<UserCommand>() {

			@Override
			public UserCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserCommand userCommand = new UserCommand(rs.getString(1), sql, sql,null);
				System.out.println(userCommand.getUserid());
				return userCommand;
			}
		},email);
	}
	
	//============================================ 비밀번호 찾기 ================================================
	public List<UserCommand> findPassword(String userid, String email){
		String sql ="SELECT passwd FROM User WHERE userid = ? AND email = ?";
		return jdbcTemplate.query(sql, new RowMapper<UserCommand>() {

			@Override
			public UserCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserCommand userCommand = new UserCommand(null, rs.getString(1), null,null);
				System.out.println(userCommand.getPasswd());
				return userCommand;
			}
		},userid,email);
	}

		
}
