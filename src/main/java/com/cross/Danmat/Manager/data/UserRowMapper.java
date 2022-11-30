package com.cross.Danmat.Manager.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cross.Danmat.User.Command.UserCommand;

public class UserRowMapper implements RowMapper<UserCommand> {

	@Override
	public UserCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserCommand user = new UserCommand(rs.getString("userid"),rs.getString("passwd")
				,rs.getString("email"), rs.getDate("regDate"));
		return user;
	}
}
