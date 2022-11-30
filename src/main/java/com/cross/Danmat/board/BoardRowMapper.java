package com.cross.Danmat.board;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.board.domain.Board;

public class BoardRowMapper implements RowMapper<Board> {

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board board = new Board(rs.getInt("board_idx"), rs.getString("notice"),
						rs.getString("userId"), rs.getString("title"), rs.getNString("content"),
						rs.getDate("createDate"), rs.getDate("updateDate"),	rs.getDate("deleteDate"),
						rs.getString("delete_yn"), rs.getLong("readCount"),	rs.getLong("replyCount"));
		return board;
	}
}
