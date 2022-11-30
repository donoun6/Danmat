package com.cross.Danmat.WordRelay.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.WordRelay.Command.PointCommand;
import com.cross.Danmat.WordRelay.Command.UsedCommand;
import com.cross.Danmat.WordRelay.Command.WordCommand;
import com.cross.Danmat.WordRelay.Domain.Word;

public class WordRelayDao {
	JdbcTemplate jdbcTemplate;

	//============================================ JDBC data ================================================
	public WordRelayDao(DataSource datasource) {
		super();
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	//============================================ 모든 단어 ================================================
	public List<Word> findAll(){
		String sql = "SELECT * FROM word";
		
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word word = new Word(rs.getString("word"), rs.getString("def"));
				return word;
			}
		});
	}
	
	//============================================ 무작위 단어 출력 ================================================
	public List<Word> randomWord(){
		String sql = "SELECT * FROM word  ORDER BY RAND() limit 1;";
		
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word word = new Word(rs.getString("word"), rs.getString("def"));
				return word;
			}
		});
	}
		
	//============================================ 입력단어 DB유무 체크  ================================================
	public int checkWord(String word) {
		try {
			String sql = "SELECT * FROM word WHERE word = ? ORDER BY RAND() limit 1 ";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString("word").contentEquals(word)){
						System.out.println("해당 단어는 DB에 등록된 단어");
						return 0;
					}
					return null;
				}
			},word);
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("해당 단어는 DB에등록된 단어가 아닙니다.");
			return 1;
		}
	}
	//============================================ 입력값 끝말에 맞는 랜덤 단어 추출 ================================================
	public List<Word> randomSplWord(String word){
		String sql = "SELECT * FROM word WHERE word LIKE ? ORDER BY RAND() limit 1";
		try {
			return jdbcTemplate.query(sql, new RowMapper<Word>() {

				@Override
				public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
					Word word = new Word(rs.getString("word"), rs.getString("def"));
					return word;
				}
			},word);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	//============================================ 사용단어 저장 ================================================
	public void isuse(String word) {
			String sql = "INSERT INTO isuse (usedWord) VALUES (?)";
			jdbcTemplate.update(sql,word);
			System.out.println("word는 사용단어에 추가되었습니다.");
		}
	
	//============================================ 사용단어 체크  ================================================
	public int usedWord(String word) {
		try {
			String sql = "SELECT * FROM isuse WHERE usedWord = ? ";
			return jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString("usedWord").contentEquals(word)){
						System.out.println("해당 단어는 사용된 단어");
						return 1;
					}
					return null;
				}
			},word);
		} catch (IncorrectResultSizeDataAccessException e) {
			System.out.println("해당 단어는 사용된 단어가 아닙니다.");
			return 0;
		}
	}
	
	//============================================ 사용단어 초기화 ================================================
	public void delete () {
		String sql = "TRUNCATE isuse";
		jdbcTemplate.update(sql);
		System.out.println("사용단어가 초기화 되었습니다.");
	}
	
	//============================================ 제시어 ================================================
	public void answer(String word) {
		String sql = "INSERT INTO answer (answerWord) VALUES (?)";
		System.out.println("answer 단어 등록");
		jdbcTemplate.update(sql,word);
	}
	
	//============================================ 사용 제시어 삭제 ================================================
	public void delete2 () {
		String sql = "TRUNCATE answer";
		jdbcTemplate.update(sql);
		System.out.println("answer 초기화 되었습니다.");
	}
	
	//============================================ 출력 단어 체크  ================================================
	public List<WordCommand> findAnswer(){
		String sql = "SELECT * FROM answer";
		return jdbcTemplate.query(sql, new RowMapper<WordCommand>() {

			@Override
			public WordCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				WordCommand word = new WordCommand(rs.getString("answerWord"));
				return word;
			}
		});
	}
	
	//============================================ 점수 ================================================
	public void point(int point){
		String sql = "INSERT INTO point (point) VALUES(?)";
		jdbcTemplate.update(sql, point);
	}
	
	//============================================ 점수 등록================================================
	public List<PointCommand> addPoint() {
		String sql = "Select sum(point) from point";
		return jdbcTemplate.query(sql, new RowMapper<PointCommand>() {

			@Override
			public PointCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				PointCommand pointCommand = new PointCommand(rs.getInt(1));
				return pointCommand;
			}
		});
	}
	
	//============================================ 점수 초기화 ================================================
	public void delete3 () {
		String sql = "TRUNCATE point";
		jdbcTemplate.update(sql);
		System.out.println("point 초기화 되었습니다.");
	}
	
	//============================================ 사용단어 추출 ================================================
	public List<UsedCommand> findUsed(){
		String sql = "SELECT * FROM isuse";
		
		return jdbcTemplate.query(sql, new RowMapper<UsedCommand>() {

			@Override
			public UsedCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				UsedCommand word = new UsedCommand(rs.getString("usedWord"));
				return word;
			}
		});
	}
	
}	
