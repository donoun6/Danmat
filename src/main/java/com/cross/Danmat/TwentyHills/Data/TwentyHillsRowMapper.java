package com.cross.Danmat.TwentyHills.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.TwentyHills.Domain.TwentyHills;

public class TwentyHillsRowMapper implements RowMapper<TwentyHills>{

	@Override
	public TwentyHills mapRow(ResultSet rs, int rowNum) throws SQLException {
		TwentyHills twentyHills = new TwentyHills(rs.getString("word")
				, rs.getString("def") , rs.getString("ex"));
		return twentyHills;
	}

}
