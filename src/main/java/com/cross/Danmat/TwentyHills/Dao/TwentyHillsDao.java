package com.cross.Danmat.TwentyHills.Dao;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cross.Danmat.TwentyHills.Data.TwentyHillsRowMapper;
import com.cross.Danmat.TwentyHills.Domain.TwentyHills;

public class TwentyHillsDao {
	JdbcTemplate jdbcTemplate;

	public TwentyHillsDao(DataSource datasource) {
		super();
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	public List<TwentyHills> findAll(){
		String sql = "SELECT * FROM word";
		
		return jdbcTemplate.query(sql, new TwentyHillsRowMapper());
	}
	
	public TwentyHills findAllByWid(long wid){
		String sql = "SELECT * FROM word WHERE wid = ?";
		
		return jdbcTemplate.queryForObject(sql, new TwentyHillsRowMapper(), wid);
	}
}
