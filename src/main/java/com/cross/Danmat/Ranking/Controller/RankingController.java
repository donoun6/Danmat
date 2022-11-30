package com.cross.Danmat.Ranking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ranking")
public class RankingController {
	
	//============================================index page================================================
	@GetMapping
	public String rankingPage() {
		return "ranking/ranking";
	}
	
}
