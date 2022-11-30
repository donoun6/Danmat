package com.cross.Danmat.User.Service;

import java.util.List;

import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.User.Dao.UserDao;

public class UserServiceImpl implements UserService{
	UserDao userDao = null;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(UserCommand user) {
		userDao.addUser(user);
	}

	@Override
	public int checkingId(String userid) {
		return userDao.checkingId(userid);
	}

	
	@Override
	public int checkingEmail(String email) {
		return userDao.checkingEmail(email);
	}
	
	@Override
	public int login(String userid, String passwd) {
		return userDao.login(userid,passwd);
	}

	@Override
	public List<UserCommand> findId(String email) {
		return userDao.findId(email);
	}

	@Override
	public List<UserCommand> findPassword(String userid, String email) {
		return userDao.findPassword(userid,email);
	}


}
