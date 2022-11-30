package com.cross.Danmat.Game.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("game")
public class GameController {
	
	//============================================index page================================================
	@GetMapping
	public String gamePage() {
		return "game/game";
	}
	
}
