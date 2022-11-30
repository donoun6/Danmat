package com.cross.Danmat.crossWord.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.crossWord.command.CountCommand;
import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.domain.Word;

public class CrossDao {
	
	
	private JdbcTemplate jdbcTemplate;
	
	
	public CrossDao() {
		super();
	}
	
	public CrossDao(DataSource datasource) {
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
//	CrossWord crossWord;
	
	public List<Word> allWord(Word word) {
		String sql = "SELECT * FROM word";
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word word = new Word(rs.getInt("wid"), rs.getString("word"),
						rs.getInt("len"), rs.getInt("grade"), rs.getString("category"),
						rs.getString("category2"), rs.getString("def"), rs.getString("ex"));
				return word;
			}
		});
	}
	
	public List<Word> randomWord( Word word) {
		String sql = "SELECT * FROM word len > 2 WHERE ORDER BY RAND() limit 1";
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word word = new Word(rs.getInt("wid"), rs.getString("word"),
						rs.getInt("len"), rs.getInt("grade"), rs.getString("category"),
						rs.getString("category2"), rs.getString("def"), rs.getString("ex"));
				return word;
			}			
		});
	}
	
	public List<Word> firstWord( Word word) {
		String sql = "SELECT * FROM word WHERE len = 4 ORDER BY RAND() limit 1";
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word word = new Word(rs.getInt("wid"), rs.getString("word"),
						rs.getInt("len"), rs.getInt("grade"), rs.getString("category"),
						rs.getString("category2"), rs.getString("def"), rs.getString("ex"));
				return word;
			}			
		});
	}
	
	/**
	 * 랜덤으로 check값을 가진 단어를 추출해낸다
	 */
	public List<Word> checkWord(String check) {
		String sql = "SELECT * FROM word WHERE word LIKE ? ORDER BY RAND() limit 1";
		return jdbcTemplate.query(sql, new RowMapper<Word>() {

			@Override
			public Word mapRow(ResultSet rs, int rowNum) throws SQLException {
				Word crossWord = new Word(rs.getInt("wid"), rs.getString("word"),
						rs.getInt("len"), rs.getInt("grade"), rs.getString("category"),
						rs.getString("category2"), rs.getString("def"), rs.getString("ex"));
				return crossWord;
			}
			
		},"%" + check + "%");
	}
	
	
	
	
	
	/**
	 * 관리자 페이지에서 게임판 생성을 위한 메소드
	 * @param crossword
	 */
	public void addToList ( Crossword crossword ) {
		String sql = "INSERT INTO CWList (gid, gameSize, wordNum, dir, word, xLocation, yLocation) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, crossword.getGid(), crossword.getGameSize(), crossword.getWordNum(), crossword.getDir(),
				crossword.getWord(), crossword.getXLocation(), crossword.getYLocation());
	}
	
	
	/**
	 * 관리자 페이지에서 게임 리스트를 확인 후 삭제할때 사용할 메소드
	 * @param crossword
	 */
//	public void deleteList ( Crossword crossword ) {
//		String sql = "DELETE FROM CWList WHERE gid=?";
//		jdbcTemplate.update(sql, new RowMapper<Crossword>() {
//			@Override
//			public Crossword mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Crossword crossword = new Crossword(rs.getInt("gid"), rs.getInt("gameSize"), rs.getInt("wordNum"), rs.getString("dir"), 
//						rs.getString("word"), rs.getInt("xLocation"), rs.getInt("yLocation"));
//				return crossword;
//			}
//		}, crossword.getGid());
//	}
	public void deleteList ( int gid ) {
		String sql = "DELETE FROM CWList WHERE gid=?";
		jdbcTemplate.update(sql, gid);
	}
	
	
	
	
	/**
	 * 마지막으로 추가된 게임의 순번 불러오기
	 * @param crossword
	 * @return
	 */
	public GidCommand lastCWNum ( Crossword crossword ) {
		String sql = "SELECT gid FROM CWList ORDER BY gid DESC limit 1";
		return jdbcTemplate.queryForObject(sql, new RowMapper<GidCommand>() {

			@Override
			public GidCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				GidCommand gidCommand = new GidCommand(rs.getInt("gid"));
				return gidCommand;
			}
		});
	}
	
	/**
	 * 중복되는 gid값을 무시하고 카운팅 하기 위해 만든 메소드
	 * @param cwList
	 * @return
	 */
	public List<GidCommand> allGid () {
		String sql = "SELECT DISTINCT gid FROM CWList ORDER BY gid ASC";
		return jdbcTemplate.query(sql, new RowMapper<GidCommand>() {

			@Override
			public GidCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				GidCommand gidCommand = new GidCommand(rs.getInt("gid"));
				return gidCommand;
			}
		});
	}
	
	
	/**
	 * gid값이 같은 모든 list들을 뽑아오기 - 게임판 생성, 게임판 보기 등의 기능에 사용되는 메소드입니다.
	 * @param crossword
	 * @return
	 */
	public List<Crossword> findByGid ( Crossword crossword ) {
		String sql = "SELECT * FROM CWList WHERE gid=?";
		return jdbcTemplate.query(sql, new RowMapper<Crossword>() {
			@Override
			public Crossword mapRow(ResultSet rs, int rowNum) throws SQLException {
				Crossword crossword = new Crossword(rs.getInt("gid"), rs.getInt("gameSize"), rs.getInt("wordNum"), rs.getString("dir"), 
						rs.getString("word"), rs.getInt("xLocation"), rs.getInt("yLocation"));
				return crossword;
			}
		},crossword.getGid());
	}
	
	
	public List<Crossword> wordNumByGid(Crossword crossword) {
		String sql = "SELECT COUNT(wordNum) FROM CWList WHERE gid=?";
		return jdbcTemplate.query(sql, new RowMapper<Crossword>() {

			@Override
			public Crossword mapRow(ResultSet rs, int rowNum) throws SQLException {
				Crossword crossword = new Crossword(rs.getInt("gid"), rs.getInt("gameSize"), rs.getInt("wordNum"), rs.getString("dir"), 
						rs.getString("word"), rs.getInt("xLocation"), rs.getInt("yLocation"));
				return crossword;
			}
		}, crossword.getGid());
	}
	
	
	/**
	 * 랜덤으로 게임을 고를 시 사용할 메소드 ( 게임 번호만 추출 )
	 * @param crossword
	 * @return
	 */
	public GidCommand randomGid ( Crossword crossword ) {
		String sql = "SELECT gid FROM CWList WHERE gid ORDER BY RAND() limit 1";
		return jdbcTemplate.queryForObject(sql, new RowMapper<GidCommand>() {

			@Override
			public GidCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				GidCommand gidCommand = new GidCommand(rs.getInt("gid"));
				return gidCommand;
			}
		});
	}
	
	/**
	 * 위의 randomGid로 뽑아온 gid값을 가진 게임판 호출
	 * @param crossword
	 * @return
	 */
	public List<Crossword> playGame( Crossword crossword ) {
		String sql = "SELECT * FROM CWList WHERE gid = ?";
		return jdbcTemplate.query(sql, new RowMapper<Crossword>() {

			@Override
			public Crossword mapRow(ResultSet rs, int rowNum) throws SQLException {
				Crossword crossword = new Crossword(rs.getInt("gid"), rs.getInt("gameSize"), rs.getInt("wordNum"), rs.getString("dir"), 
						rs.getString("word"), rs.getInt("xLocation"), rs.getInt("yLocation"));
				return crossword;
			}
			
		});
		
	}
	
}
