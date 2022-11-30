package com.cross.Danmat.User.Service;

import java.util.List;

import com.cross.Danmat.User.Command.UserCommand;

public interface UserService {
	
	void addUser(UserCommand user);
	
	int checkingId(String userid);
	
	int checkingEmail(String email);
	
	int login(String userid,String passwd);
	
	List<UserCommand> findId(String email);
	
	List<UserCommand> findPassword(String userid, String email);
}
