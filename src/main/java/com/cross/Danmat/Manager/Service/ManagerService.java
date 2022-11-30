package com.cross.Danmat.Manager.Service;

import java.time.LocalDate;
import java.util.List;

import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.board.domain.Board;

public interface ManagerService {

	int mglogin(String mgid, String mgpasswd);   
	
	List<UserCommand> searchId(String email);

	List<UserCommand> searchEmail(String userid);
	
	List<UserCommand> AllUserList();
	
	void DeleteUser(String userid);
	
	List<UserCommand> getNewUserCount(LocalDate now);
	
	public List<Board> newBoardList(LocalDate date);
	
	void DeleteBoard(String board_idx);
	
	public List<Board> BoardList();
}
