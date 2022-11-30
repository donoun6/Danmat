package com.cross.Danmat.crossWord.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.command.CountCommand;
import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.service.CrossServiceImpl;

public class DaoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		CrossServiceImpl crossService = context.getBean("crossServiceImpl", CrossServiceImpl.class);
		
//		Crossword crossword = new Crossword();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		/**
//		 * crossService.allGid().get(i).getTakeGid() - > 게임 번호 추출
//		 * crossService.findByGid(crossword).size() -> 게임번호에 따른 단어 갯수
//		 * crossService.findByGid(crossword).get(i) -> 게임번호에 따른 단어에 대한 상세 정보
//		 */
//		int a = crossService.allGid().size();
//		
//		List<Crossword>[] game = new ArrayList[a];
//		for (int i = 0; i < a; i++) {
//			game[i] = new ArrayList<Crossword>();
//			crossword.setGid(crossService.allGid().get(i).getTakeGid());
//			
//			// game[i].get(j) 순번대로 CWList 한줄씩 들어가 있는 상태 입니다 - i : gid값이 동일, j : 동일한 gid값을 가진 CWList
//			for (int j = 0; j < crossService.findByGid(crossword).size(); j++) {
//				game[i].add(crossService.findByGid(crossword).get(j));
//			}
//		}
//		
//		//crossword 확인
//		for (int i = 0; i < a; i++) {
//			//game의 각 배열의 cwlist ( crossword )
//			for (int j = 0; j < game[i].size(); j++) {
//				System.out.println(game[i].get(j));
//			}
//		}
//		
//		/**
//		 * a는 총 게임의 수,
//		 * a배열 안에 새로운 ArrayList[h]을 생성(단어 하나당 1리스트),
//		 * 그리고 그 내부에 ArrayList[h][i]를 생성(단어 하나하나의 정보)
//		 * 각 필요한 위치에 자리잡게 해줌 
//		 */
//		
//		//gid의 갯수인 a의 값만큼의 어레이리스트 작성
//		ArrayList<String>[][] list = new ArrayList[a][20];
//		for (int i = 0; i < a; i++) {
//			int y = game[i].get(0).getGameSize();
//			System.out.println(y + "gameSize");
//			for(int j = 0; j < y; j++) {
//				list[i][j] = new ArrayList<String>();
//				for (int s = 0; s < y; s++) {
//					list[i][j].add("ㅇ");
//				}
//			}
//		}
//		
////		for (int i = 0; i < a; i++) {
////			for (int j = 0; j < game[i].get(0).getGameSize(); j++) {
////				System.out.println(list[i][j]);
////			}
////			System.out.println();
////			System.out.println();
////		}
////		System.out.println();
//		
//		
//		//반복 : 전체 게임 수
//		for (int i = 0; i < a; i++) {
//			
//			//반복 : 각 게임 내부 crossWord data
//			int g = game[i].size();
//			System.out.println("현재 gid : " + game[i].get(0).getGid() + ", " + "현재 게임 사이즈 : " + g);
//			
//			for (int j = 0; j < g; j++) {
//				
//				//해당 단어의 길이 ff
//				int ff = game[i].get(j).getWord().length();
//				//해당 단어의 방향이 x 일경우 세로로 단어를 넣어주는 조건식
//				if(game[i].get(j).getDir().equals("x")) {
//					for (int k = 0; k < ff; k++) {
//						list[i][game[i].get(j).getYLocation()].set(game[i].get(j).getXLocation() + k,game[i].get(j).getWord().substring(k, k+1));
//					}
//				}
//				//해당 단어의 방향이 y일 경우 가로로 단어를 넣어주는 조건식
//				else if(game[i].get(j).getDir().equals("y")) {
//					for (int k = 0; k < ff; k++) {
//						list[i][game[i].get(j).getYLocation() + k].set(game[i].get(j).getXLocation(), game[i].get(j).getWord().substring(k, k+1));
//					}
//				}
//			}
//		}
//		
//		//제대로 들어갔는지 확인
//		for (int i = 0; i < a; i++) {
//			int g = game[i].get(0).getGameSize();
//			System.out.println("-------------------------------------------------");
//			for (int j = 0; j < g; j++) {
//				System.out.println(list[i][j]);
//			}
//		}
//		System.out.println(list[0][4].get(1)); // 음절 위치에 제대로 들어갔는지 확인
		List<GidCommand> list = new ArrayList<GidCommand>();
		crossService.allGid().forEach(c -> list.add(new GidCommand(c.getTakeGid())));
		System.out.println(list);
//		crossService.deleteList(list.get(0).getTakeGid());
		
		context.close();
	}
}
