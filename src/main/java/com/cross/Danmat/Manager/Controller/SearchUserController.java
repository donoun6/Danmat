package com.cross.Danmat.Manager.Controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.Manager.Service.ManagerServiceImpl;
import com.cross.Danmat.User.Command.UserCommand;

@Controller("Manager.Controller.searchUserController")
@RequestMapping("manager/searchUser")  //관리자 전용 회원 검색
public class SearchUserController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	ManagerServiceImpl managerservice = context.getBean("managerService", ManagerServiceImpl.class);

	@GetMapping     //유저 아이디 검색
	public String SearchIdForm(Model model) {
		List<UserCommand> user_list = managerservice.AllUserList();
		model.addAttribute("user_list", user_list);
		return "manager/search/searchUser"; 
	}
	
	@PostMapping
	public String searchId(@RequestParam("type")String type,
			@RequestParam("Name") String name, Model model) {
		System.out.println(type);
		System.out.println(name);
		if(type.equals("id")) {
			List<UserCommand> searchId = managerservice.searchId(name);
			model.addAttribute("user_list",searchId);
		}else if(type.equals("email")){
			List<UserCommand> searchemail = managerservice.searchEmail(name);
			model.addAttribute("user_list", searchemail);
		}
			return "manager/search/searchUser";
	}
	
//	@GetMapping   // 유저 이메일 검색
//	public String SearchEmailForm(Model model) {
//		return "search/searchImpo";
//	}
//	
//	@PostMapping
//	public String search(@ModelAttribute("user") UserCommand user, Model model) {
//		List<UserCommand> searchEmail = userService.searchEmail(user.getUserid());
//		model.addAttribute("searchEmail",searchEmail);
//			return "search/searchImpo";
//		}
//	
//	@GetMapping   // 유저 목록 검색
//	public String getAllUserForm(Model model) {
//		return "search/searchUserList";
//	}
//	
//	@PostMapping
//	public String getAllUserForm(@ModelAttribute("user") UserCommand user, Model model) {
//		List<UserCommand> searchUserList = userService.SearchUserList(user.getUserid(), user.getEmail());
//		model.addAttribute("SearchUserList",searchUserList);
//		System.out.println(searchUserList);
//			return "search/searchUserList";
//		}	

}
