package com.cross.Danmat.Manager.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.dao.CrossDao;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.service.CrossService;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;

@Controller
@RequestMapping("/manager/cross/crossSearch")
public class CrossSearchController {
	//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
	//CrossServiceImpl service = context.getBean("crossServiceImpl", CrossServiceImpl.class);
	
	@Autowired
	private CrossService crossService;
	
	@ModelAttribute("gidList")
	public List<GidCommand> getGidList() {
		List<GidCommand> list = new ArrayList<GidCommand>();
		crossService.allGid().forEach(c -> list.add(new GidCommand(c.getTakeGid())));
		return list;
	}
	
	
	@GetMapping
	public String crossSearchForm(@ModelAttribute("GidList") Crossword crossword, Model model, GidCommand gidCommand) {
		
		
		/**
		 * crossService.allGid().get(i).getTakeGid() - > 게임 번호 추출
		 * crossService.findByGid(crossword).size() -> 게임번호에 따른 단어 갯수
		 * crossService.findByGid(crossword).get(i) -> 게임번호에 따른 단어에 대한 상세 정보
		 */
		int a = crossService.allGid().size();
		
		//각 gid값에 맞는 crossword들의 정보를 호출하기 위한 game 리스트
		List<Crossword>[] game = new ArrayList[a];
		for (int i = 0; i < a; i++) {
			game[i] = new ArrayList<Crossword>();
			crossword.setGid(crossService.allGid().get(i).getTakeGid());
			
			// game[i].get(j) 순번대로 CWList 한줄씩 들어가 있는 상태 입니다 - i : gid값이 동일, j : 동일한 gid값을 가진 CWList
			for (int j = 0; j < crossService.findByGid(crossword).size(); j++) {
				game[i].add(crossService.findByGid(crossword).get(j));
			}
		}
		
		//crossword 확인
		for (int i = 0; i < a; i++) {
			//game의 각 배열의 cwlist ( crossword )
			for (int j = 0; j < game[i].size(); j++) {
				System.out.println(game[i].get(j));
			}
		}
		
		/**
		 * a는 총 게임의 수,
		 * a배열 안에 새로운 ArrayList[h]을 생성(단어 하나당 1리스트),
		 * 그리고 그 내부에 ArrayList[h][i]를 생성(단어 하나하나의 정보)
		 * 각 필요한 위치에 자리잡게 해줌 
		 */
		
		//gid의 갯수인 a의 값만큼의 어레이리스트 작성
		ArrayList<String>[][] list = new ArrayList[a][20];
		for (int i = 0; i < a; i++) {
			int y = game[i].get(0).getGameSize();
			System.out.println(y + "gameSize");
			for(int j = 0; j < y; j++) {
				list[i][j] = new ArrayList<String>();
				for (int s = 0; s < y; s++) {
					list[i][j].add("");
				}
			}
		}
//		공백 잘 들어갔는지 확인
//		for (int i = 0; i < a; i++) {
//			for (int j = 0; j < game[i].get(0).getGameSize(); j++) {
//				System.out.println(list[i][j]);
//			}
//			System.out.println();
//			System.out.println();
//		}
//		System.out.println();
		
		
		//반복 : 전체 게임 수
		for (int i = 0; i < a; i++) {
			
			//반복 : 각 게임 내부 crossWord data
			int g = game[i].size();
			System.out.println("현재 gid : " + game[i].get(0).getGid() + ", " + "현재 게임 사이즈 : " + g);
			
			for (int j = 0; j < g; j++) {
				
				//해당 단어의 길이 ff
				int ff = game[i].get(j).getWord().length();
				//해당 단어의 방향이 x 일경우 세로로 단어를 넣어주는 조건식
				if(game[i].get(j).getDir().equals("x")) {
					for (int k = 0; k < ff; k++) {
						list[i][game[i].get(j).getYLocation()].set(game[i].get(j).getXLocation() + k,game[i].get(j).getWord().substring(k, k+1));
					}
				}
				//해당 단어의 방향이 y일 경우 가로로 단어를 넣어주는 조건식
				else if(game[i].get(j).getDir().equals("y")) {
					for (int k = 0; k < ff; k++) {
						list[i][game[i].get(j).getYLocation() + k].set(game[i].get(j).getXLocation(), game[i].get(j).getWord().substring(k, k+1));
					}
				}
			}
		}
		
		//제대로 들어갔는지 확인
		for (int i = 0; i < a; i++) {
			int g = game[i].get(0).getGameSize();
			System.out.println("-------------------------------------------------");
			for (int j = 0; j < g; j++) {
//				System.out.println(list[i][j]);
			}
		}
		System.out.println(list[0][4].get(1)); // 음절 위치에 제대로 들어갔는지 확인
		
		StringBuilder sb = new StringBuilder();
		
		ArrayList<String>[] game_table = new ArrayList[a];
		for (int i = 0; i < a; i++) {
			int gameSize = game[i].get(0).getGameSize();
			game_table[i] = new ArrayList<String>();
			sb.append("<div class='crossword gnum" + game[i].get(0).getGid() + "'><table>");
			for (int j = 0; j < gameSize; j++) {
				sb.append("<tr>");
				for (int k = 0; k < gameSize; k++) {
					if(list[i][j].get(k).equals("")) {
						sb.append("<td empty></td>");
					}
					else {
						sb.append("<td>" + list[i][j].get(k) + "</td>");
					}
				}
				sb.append("</tr>");
			}
			sb.append("</table></div>");
			game_table[i].add(sb.toString());
			System.out.println(sb.toString());
			// StringBuilder 초기화
			sb.setLength(0);
		}
		model.addAttribute("gameTable", game_table);
		
		
//		//Delete를 위해 model에 gidList에 담아두기
//		List<GidCommand> gidList = crossService.allGid();
//		model.addAttribute("gidList", gidList);
		
		
		return "manager/cross/crossSearch";
	}
	
	
//	//삭제를 위한 post
	@PostMapping
	public String deleteCW(@RequestParam("gid")int gid) {
		crossService.deleteList(gid);
		return "manager/cross/crossAfterDeleteCW";
	}
//	@GetMapping
//	public String DeleteCWList(@RequestParam("takeGid")GidCommand gidCommand) {
//		crossService.deleteList(gidCommand);
//		return "redirect:/crossSearch";
//	}
}
