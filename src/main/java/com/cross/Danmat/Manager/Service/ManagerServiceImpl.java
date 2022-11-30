package com.cross.Danmat.Manager.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.cross.Danmat.Manager.Dao.ManagerDao;
import com.cross.Danmat.Manager.domain.Manager;
import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.User.Dao.UserDao;
import com.cross.Danmat.board.domain.Board;

public class ManagerServiceImpl implements ManagerService{
	ManagerDao managerDao;

	public ManagerServiceImpl(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}


	@Override
	public int mglogin(String mgid, String mgpasswd) {
		int count = 0;
		List<Manager> manager_list = managerDao.isManager();
		
		for(int i = 0; i<manager_list.size(); i++) {
			if(manager_list.get(i).getMgid().equals(mgid)) {
				count++;
				if(manager_list.get(i).getMgpasswd().equals(mgpasswd)) {
					count++;
				}else {
					return count;
				}
			}else {
				return count;
			}
		}
		return count;
	}

	@Override
	public List<UserCommand> searchId(String userid) {
		return managerDao.SearchIdForm(userid);
	}

	@Override
	public List<UserCommand> searchEmail(String email) {
		return managerDao.SearchEmailForm(email);
	}


	@Override
	public List<UserCommand> AllUserList() {
		return managerDao.getAllUserForm();
	}


	@Override
	public void DeleteUser(String userid) {
		managerDao.DeleteUser(userid);
	}


	@Override
	public List<UserCommand> getNewUserCount(LocalDate now) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = now.format(formatter);
		return managerDao.getNewUserCount(date);
	}
	
	@Override
	public List<Board> BoardList() {
		// TODO Auto-generated method stub
		return managerDao.boardList();
	}


	@Override
	public void DeleteBoard(String board_idx) {
		// TODO Auto-generated method stub
		managerDao.DeleteBoard(board_idx);
	}


	@Override
	public List<Board> newBoardList(LocalDate date) {
		// TODO Auto-generated method stub
		return managerDao.newboardList(date);
	}
}
