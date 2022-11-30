package com.cross.Danmat.User.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
	private String userid;
	private String passwd;
	private String email;
	
	public User() {
		super();
	}
}
