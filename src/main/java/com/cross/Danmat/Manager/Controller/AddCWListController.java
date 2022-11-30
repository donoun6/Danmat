package com.cross.Danmat.Manager.Controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;
import com.cross.Danmat.crossWord.service.MakeCW;

@Controller("addCWListController")
@RequestMapping("manager/cross/crossAdd")
public class AddCWListController {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	CrossServiceImpl service = context.getBean("crossServiceImpl",CrossServiceImpl.class);
	
	@GetMapping
	public String addCWListForm() {
		return "/manager/cross/crossAdd";
	}
	
	@PostMapping
	public String addCWList(Model model,@RequestParam("size")int size, @RequestParam("repeatNum")int repeatNum) {
		
		AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MakeCW makeCW = context2.getBean("makeCW", MakeCW.class);
		
		makeCW.MakeCrossWord(size, repeatNum);
		model.addAttribute("size", size);
		model.addAttribute("repeatNum", repeatNum);
		context2.close();
		
		return "/manager/cross/addComplete";
	}
	
	
	
	
	
}
