package com.cross.Danmat.Manager.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("Manager.Controller.mgLogoutController")
@RequestMapping("manager/logout")
public class MgLogoutController {
	
	//관리자 로그아웃
	@GetMapping
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "manager/mglogIn/mglogIn";
		
	}
}
