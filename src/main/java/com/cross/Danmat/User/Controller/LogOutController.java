package com.cross.Danmat.User.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("logOut")
public class LogOutController {
	
	//============================================ 로그아웃 ================================================
	@GetMapping
	public String logout(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		int logOut = 1;
		model.addAttribute("logOut",logOut);
		
		return "user/logOut/logOut";
	}
}
