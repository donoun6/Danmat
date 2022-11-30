package com.cross.Danmat.Manager.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.Manager.domain.Manager;
import com.cross.Danmat.User.Command.UserCommand;

public class ManagerRowMapper implements RowMapper<Manager> {

	@Override
	public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
		Manager manager = new Manager(rs.getString("mgid"),rs.getString("mgpasswd"));
		return manager;
	}
}
