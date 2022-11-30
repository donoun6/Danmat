package com.cross.Danmat.Manager.Controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;

@Controller("crossSettingController")
@RequestMapping("manager/cross/crossList")
public class CrossSettingController {  //크로스 워드 게임 설정 화면
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	CrossServiceImpl service = context.getBean("crossServiceImpl", CrossServiceImpl.class);

	@GetMapping    
	public String CrossSettingPage(Model model) {
		return "/manager/cross/crossList";
		
	}
	
	@PostMapping
	public String CrossAddForm(Model model) {
		return "/manager/cross/crossAdd";
	}
	
}
