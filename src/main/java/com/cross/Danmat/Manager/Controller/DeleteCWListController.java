package com.cross.Danmat.Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.service.CrossService;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;


@Controller
@RequestMapping("manager/cross/crossDelete")
public class DeleteCWListController {
	
//	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
//	CrossServiceImpl crossService = context.getBean("crossServiceImpl",CrossServiceImpl.class);
	
	@Autowired
	private CrossService crossService;
	
	@GetMapping
	public String DeleteCWList(@RequestParam("gid")int gid) {
		crossService.deleteList(gid);
		return "redirect:/manager/cross/crossSearch";
	}
	
}
