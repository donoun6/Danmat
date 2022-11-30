package com.cross.Danmat.TwentyHills.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.TwentyHills.Domain.TwentyHills;
import com.cross.Danmat.TwentyHills.Service.TwentyHillsServiceImpl;

@Controller
@RequestMapping("/Game/TwentyHills")
public class TwentyHillsController {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	TwentyHillsServiceImpl twentyHillsService = context.getBean("twentyHillsService", TwentyHillsServiceImpl.class);
	Random rd = new Random();
	
	@GetMapping
	public String TH_Games(Model model, TwentyHills twentyHills) {
		twentyHills = twentyHillsService.load_word();
		String mosaic = "";
		String input = "<div id=input><label>단어 : </label>";
		String word = twentyHills.getWord();
		for(int i = 0; i< word.length(); i++) {
			mosaic += "○";
		}
		List<String> hint = new ArrayList<String>();
		String[] hint_list = twentyHills.getDiscription().split("/");
		String[] Example_list = twentyHills.getExample().replaceAll(word, mosaic).split("/");
		int life = 20;
		hint.add(hint_list[rd.nextInt(hint_list.length)]);
		for(int i = 0; i < Example_list.length; i++) {
			hint.add(Example_list[i]);			
		}
		for(int i = 0; i< word.length(); i++) {
			input += "<input type='text' class='input' maxlength='1'>";
		}
		input += "</div>";
		model.addAttribute("input", input);
		model.addAttribute("hint", hint);
		model.addAttribute("word", word);
		model.addAttribute("life", life);
		return "TwentyHills/TwentyHills";
	}
}
