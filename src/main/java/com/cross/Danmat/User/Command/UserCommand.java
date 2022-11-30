package com.cross.Danmat.User.Command;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserCommand {
	private String userid;
	private String passwd;
	private String email;
	private Date regDate;
	
	public UserCommand() {
		super();
	}
}
