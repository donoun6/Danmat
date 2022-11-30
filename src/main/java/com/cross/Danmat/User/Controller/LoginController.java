package com.cross.Danmat.User.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.User.Command.UserCommand;
import com.cross.Danmat.User.Service.UserService;

@Controller
@RequestMapping("logIn")
public class LoginController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	UserService userService = context.getBean("userService", UserService.class);
	
	//============================================ 로그인 form ================================================
	@GetMapping
	public String loginPage() {
		return "user/logIn/logIn";
	}
	
	//============================================ 로그인 ================================================
	@PostMapping
	public String login(@ModelAttribute("user") UserCommand user, Model model,
			HttpServletRequest request, HttpServletResponse response,@RequestParam("url") String url) throws IOException {
		
		int result = userService.login(user.getUserid(), user.getPasswd());
		model.addAttribute("result", result);
		
		if(result == 2 ) {
			return "user/logIn/error_logIn";
		}if(user.getUserid() == null || user.getUserid().length() == 0 ) {
			return "user/signUp/error_signUp";
		}if(user.getPasswd() == null || user.getPasswd().length() == 0 ) {
			return "user/signUp/error_signUp";
		}
		
		HttpSession session = request.getSession(true); 
		session.setAttribute("userid", user.getUserid());
		context.close();
		
		return "redirect:"+url;
	}
}
