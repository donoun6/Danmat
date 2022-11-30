package com.cross.Danmat.Manager.Controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.Manager.Service.ManagerServiceImpl;

@Controller
@RequestMapping("/manager/delete_User")
public class DeleteUserController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	ManagerServiceImpl managerservice = context.getBean("managerService", ManagerServiceImpl.class);
	
	@GetMapping
	public String DeleteUser(@RequestParam("userid")String userid) {
		managerservice.DeleteUser(userid);
		return "redirect:/manager/searchUser";
	}

}
