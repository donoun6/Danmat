package com.cross.Danmat.User.Controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.User.Service.UserService;


@Controller
@RequestMapping("signUp")
public class SignUpController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	UserService userService = context.getBean("userService", UserService.class);
	
	//============================================ 회원가입 form ================================================
	@GetMapping
	public String SignUpForm(Model model) {
		model.addAttribute("user", new UserCommand());
		return "user/signUp/signUp";
	}
	
	//============================================ 회원가입 ================================================
	@PostMapping
	public String SignUp(@ModelAttribute("user") UserCommand user, Model model ) {
		model.addAttribute("user",user);
		
		int idResult = userService.checkingId(user.getUserid());
		model.addAttribute("idResult",idResult);
		
		int emailResult = userService.checkingEmail(user.getEmail());
		model.addAttribute("emailResult",emailResult);
		
		//form 입력값이 잘못되었을때 다시 회원가입페이지로
		if(idResult == 1) {
			return "user/signUp/error_signUp";
		}if(emailResult == 1) {
			return "user/signUp/error_signUp";
		}if(user.getUserid() == null || user.getUserid().length() == 0 ) {
			return "user/signUp/error_signUp";
		}if(user.getPasswd() == null || user.getPasswd().length() == 0 ) {
			return "user/signUp/error_signUp";
		}if(user.getEmail() == null || user.getEmail().length() == 0 ) {
			return "user/signUp/error_signUp";
		}
		
		userService.addUser(user);
		context.close();
		return "user/signUp/success_signUp";
	}
}
