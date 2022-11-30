package com.cross.Danmat.crossWord.service;

import java.util.ArrayList;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cross.Danmat.Config.DataSourceConfig;
import com.cross.Danmat.crossWord.dao.CrossDao;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.domain.Word;

public class MakeCW {
	
	public MakeCW() {
		super();
	}
	CrossServiceImpl crossServiceImpl;
	public MakeCW(CrossServiceImpl crossServiceImpl) {
		this.crossServiceImpl = crossServiceImpl;
	}
	public void MakeCrossWord(int size, int a) {  // 게임사이즈 size,  반복횟수 a  입력하기
		CrossServiceImpl crossService = new CrossServiceImpl();
		Crossword cwList = new Crossword();
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		crossService = context.getBean("crossServiceImpl", CrossServiceImpl.class);
		Word crossWord = new Word();
		
		int c = crossService.crossDao.lastCWNum(cwList).getTakeGid() + 1;
		
		int fullSize = (size * 2) + 1;
		
		
		ArrayList<String>[][] game_table = new ArrayList[c + a][fullSize];

		//게임에 들어간 단어를 담을 word_list	
		ArrayList<String>[] word_list = new ArrayList[c + a];
		
		//게임에 들어간 단어의 방향을 담을 word_dir
		ArrayList<String>[] word_dir = new ArrayList[c + a];
		
		//해당 단어의 시작x좌표
		ArrayList<Integer>[] xLocation = new ArrayList[c + a];
		
		//해당 단어의 시작y좌표
		ArrayList<Integer>[] yLocation = new ArrayList[c + a];
		
		//단어의 순번
		ArrayList<Integer>[] wordNum = new ArrayList[c + a];
		
		
		
		for (int b = c; b < c + a; b++) {
				
			
			word_list[b] = new ArrayList<String>();
			word_dir[b] = new ArrayList<String>();
			xLocation[b] = new ArrayList<Integer>();
			yLocation[b] = new ArrayList<Integer>();
			wordNum[b] = new ArrayList<Integer>();

			/**
			 * game_table 생성 ( size * size )
			 * size 는 각 섹션별 할당할 가로세로의 크기이다
			 */
			for (int i = 0; i < fullSize; i++) {
				game_table[b][i] = new ArrayList<>();
				for (int j = 0; j < fullSize; j++) {
					game_table[b][i].add("ㅁ");
				}
				System.out.println(game_table[b][i]);
			}
				
			
			/**
			 * 각 섹션별 할당 크기 할당
			 */
			int xStart = 0;
			int xEnd = 0;
			int yStart = 0;
			int yEnd = 0;
			
			int checkWordNum = 0;
			
			for (int y = 0; y < 2; y++) {
				for (int x = 0; x < 2; x++) {
					System.out.println("---------------------섹션 시작-----------------------");
					xStart = size * x + x;
					xEnd = size * ( x+1 ) + x;
					yStart = size * y + y ;
					yEnd = size * ( y+1 ) + y ;
					System.out.println("각 섹션의 마지막 x, y좌표 : " + xEnd + ", " + yEnd);
					
					
					/**
					 * 최초 랜덤단어 생성 ( rW1 )
					 */
					String rW1 = "";
					
					/**
					 * 
					 */
					int rW1Leng = 0;
//					System.out.println(rW1Leng);
					int yleng = yStart + y;
					int xleng = xStart + x;
					int count = 0;
					
					int yNum = 0;
					int xNum = 0;
					
					
					
					
//					k값을 증가시키면서 game테이블이 해당 단어를 가지고 있는지 확인하고 그렇지 않을 경우 랜덤단어를 다시
//					뽑아오는 식 작성하기 
					int k = size * size;
					while( k  == size * size ) {
						//더해졌던 k값을 다시 0으로 초기화 시켜준 상태에서 돌려야한다. 아니면 k 값이 size * size 만큼 계속 더해지기 때문
						k = 0;
						
						while( yleng >= yStart && yleng < yEnd && count == 0) {
							while( xleng >= xStart && xleng < xEnd && count == 0) {
								
								//각 섹션의 랜덤한 시작위치를 잡기 위한 ranNum
								int ranNum = (int)(Math.random()*1.1);
								rW1 = crossService.firstWord(crossWord).get(0).getWord();
								System.out.println("현재 랜덤단어 rW1 : " + rW1);
								rW1Leng = rW1.length() - 1;
//									System.out.println(rW1Leng);
								
								//위의 랜덤값이 1이 나오고 xleng값과 단어의 길이가 섹션의 마지막의 길이 이하인 경우
								if( ranNum == 1 && xleng + rW1Leng < xEnd ) {
									for (int i = 0; i <= rW1Leng ; i++) {
										//yleng행의 xleng+i위치의 값이 "ㅁ"와 동일할때만 집어넣기
										if(game_table[b][yleng].get(xleng + i).equals("ㅁ")) {
											game_table[b][yleng].set(xleng + i, rW1.substring(i, i+1));
											
											//단어 시작 위치를 잡는 xNum, yNum
											xNum = xleng;
											yNum = yleng;
										}
									}
									//단어를 넣었으니 count값을 1 더해주어 while문을 닫도록 만들어준다
									count++;
								}else if ( ranNum == 1 && xleng + rW1Leng > xEnd) {
									xleng++;
									count = 0;
								}else {
									xleng++;
									count = 0;
								}
								
							}
							//while문으로 한줄이 끝날 경우 xleng의 값을 0으로 초기화 시켜주고 yleng의 값을 더해주어
							//다음 줄부터 다시 검증하도록 만듦
							xleng = xStart + x;
							yleng++;
							
						}
						
						
						
						/**
						 * while문으로 생성되지 않은 섹션은 다시 yleng값과 xleng값을 되돌려 다시 위의 기능을 하도록
						 * 선언해 둔 k값이 size * size일 경우 전부 공백이라는 뜻으로, yleng값과 xleng값 초기화 시켜 준 후 다시 돌리게 만들었다 
						 */
						for (int i = yStart; i < yEnd; i++) {
							for (int j = xStart; j < xEnd; j++) {
								if(game_table[b][i].get(j).equals("ㅁ")) {
									k++;
								}
							}
						}
						if( k == size * size ) {
							yleng = yStart + y;
							xleng = xStart + x;
							count = 0;
						}
						System.out.println("현재 k 값 : " + k);
					}
					
					//단어, 단어방향 추가 - 첫단어는 무조건 가로이기때문에 x값 할당
					word_list[b].add(rW1);
					word_dir[b].add("x");
					xLocation[b].add(xNum);
					yLocation[b].add(yNum);
					checkWordNum++;
					wordNum[b].add(checkWordNum);
					
					//중복단어 체크 후 일정 값 이상까지 안돌아가도록 하는 checkDupl
					int checkDupl = 0;
					
					int makeCount = 0;
					
					while(makeCount < 150) {
						
						/**
						 * 가로인지 세로인지 검증을 해봅시다
						 */
						//2번쨰 else if문에서 yNum + i 값이 자꾸 에러를 발생시키는것 같다  // 수정 0826
						
						
						int dirx = 0;
						int diry = 0;
						
						//시작 y좌표가 0번 행일때
						//y좌표가
						if( yNum == 0 ) {
							for (int i = 0; i < rW1.length(); i++) {
								//xNum의 값은 마이너스가 될 수 없으므로 배열의 최대크기보다 작기만 하면 되는 조건식을 작성하자
								if(xNum + rW1.length() <= fullSize) {
									if(game_table[b][yNum].get(xNum + i).equals(rW1.substring(i, i+1))) {
										System.out.println("case 1-1. 가로");
										dirx++;
									}else if (game_table[b][yNum + i].get(xNum).equals(rW1.substring(i, i+1))) {
										System.out.println("case 1-1. 세로");
										diry++;
									}else {
										System.out.println("case 1-1. 미작성");
										makeCount = 150;
										dirx = 0;
										diry = 0;
									}
								}
								//xNum, rW1의 길이의 합이 게임테이블의 크기를 벗어나게 된다면 세로인 경우밖에 없다.
								else if ( xNum + rW1.length() > fullSize) {
									if (game_table[b][yNum + i].get(xNum).equals(rW1.substring(i, i+1))) {
										System.out.println("case 1-2. 세로");
										diry++;
									}else {
										System.out.println("case 1-2. 미작성");
										makeCount = 150;
										dirx = 0;
										diry = 0;
									}
								}
							}
						}
						//yNum이 0보다 크고, yNum + rW1의 길이의 합이 게임테이블 을 벗어나지 않을 경우
						else if ( yNum > 0 && yNum + rW1.length() <= fullSize) {
							for (int i = 0; i < rW1.length(); i++) {
								if(xNum + rW1.length() <= fullSize) {
									if(game_table[b][yNum].get(xNum + i).equals(rW1.substring(i, i+1))) {
										System.out.println("case 2-1. 가로");
										dirx++;
									}else if(game_table[b][yNum+i].get(xNum).equals(rW1.substring(i, i+1))) {
										System.out.println("case 2-1. 세로");
										diry++;
									}else {
										System.out.println("case 2-1. 미작성");
										makeCount = 150;
									}
								}
								//xNum, rW1의 길이의 합이 게임테이블의 크기를 벗어나게 된다면 세로인 경우밖에 없다.
								else if(xNum + rW1.length() > fullSize) {
									if(game_table[b][yNum + i].get(xNum).equals(rW1.substring(i, i+1))) {
										System.out.println("case 2-2. 세로");
										diry++;
									}else {
										System.out.println("case 2-2. 미작성");
										makeCount = 150;
										dirx = 0;
										diry = 0;
									}
								}
							}
						}
						// yNum과 단어 길이 합이 게임테이블을 벗어난다면 무조건 가로일수 밖에 없다.
						else if ( yNum + rW1.length() > fullSize) {
							for (int i = 0; i < rW1.length(); i++) {
								if(xNum + rW1.length() <= fullSize) {
									if(game_table[b][yNum].get(xNum + i ).equals(rW1.substring(i, i+1))) {
										System.out.println("case 3. 가로");
										dirx++;
									}else {
										System.out.println("case 3. 미작성");
										makeCount = 150;
										dirx = 0;
										diry = 0;
									}
								}else {
									System.out.println("case 3-2. 미작성");
									makeCount = 150;
									dirx = 0;
									diry = 0;
								}
							}
						}else {
							System.out.println("case 4. 미작성");
							makeCount = 150;
							diry = 0;
							dirx = 0;
						}
						System.out.println("가로세로값 확인하기, dirx : " + dirx + ", diry : " + diry);
						
						
						if(dirx >= 2) {
							System.out.println(rW1 + "   가로로 되어있습니다.");
						}else if(diry >= 1) {
							System.out.println(rW1 + "   세로로 되어있습니다.");
						}
						
						
						//첫번째 단어의 음절 중 랜덤으로 하나를 골라온다
						int rW1LetterNum = (int)(rW1.length() * Math.random());
						String rW1Letter = rW1.substring(rW1LetterNum, rW1LetterNum+1);
						
						//랜덤으로 골라온 음절을 이용해 두번째 단어를 추출해낸다
						String rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
//						System.out.println("연결되는 두번째 단어 : " + rW2);
						
						//1번단어와 2번단어의 연결점이 되는 음절이 2번단어의 몇번째 음절인지를 체크
						int rW2LetterNum = 0;
						checkDupl = 0;
						while(checkDupl < 50) {
							if((rW2.toString()).equals(rW1.toString())) {
								rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
								checkDupl++;								
							}else {
								checkDupl = 50;
							}
						}
						for (int i = 0; i < rW2.length(); i++) {
							if(rW1Letter.equals(rW2.substring(i, i+1))) {
								rW2LetterNum = i;
							}
						}
						
						
						System.out.println("1번째 단어 : " + rW1 + ", 겹치는 음절 : " + rW1Letter + ", 겹치는 음절의 번호 : " + (rW1LetterNum ));
						System.out.println("2번째 단어 : " + rW2 + ", 겹치는 음절 : " + rW1Letter + ", 겹치는 음절의 번호 : " + (rW2LetterNum ));
						
						
						int h = 0;
						while(  h < 50 ) {
							//카운트값을 0으로 초기화시켜주고 들어갈 자리 양 옆이 공백인지를 확인 후 넣어줍니다
							count = 0;
							
//							System.out.println("xNum : " + xNum + ", yNum : " + yNum );
//							for (int i = 0; i < fullSize; i++) {
//								System.out.println(game_table[i]);
//								
//							
							//rW1이 가로일 경우
							if(dirx >= 2 ) {
								
								// rW2의 시작부분이 0이상이어야 하고 마지막 부분이 배열의 마지막까지여야한다
								if(yNum - rW2LetterNum >= 0 && yNum + rW2.length() - rW2LetterNum <= fullSize) {
									//넣어줄 위치의 시작과 마지막부분이 비어 있는 칸인지 확인 후 넣어주자
									
//									System.out.println("가로, 현재 2번째 단어 : " + rW2);
									
									
									//rW2의 첫 음절 , rW2의 맨 마지막 음절  ==> 두개짜리 단어는 불가
									//처음이 겹치는것과 마지막이 겹치는걸 따로 분리해서 만들어야할듯??? 
									//집어넣으려는 자리 양 옆으로 공백인지 확인하고 넣어야함, 미확인시 count놔두고 확인시 count++ 해준 다음 count가 1일 경우에만 만들도록 하는건 어떨까? - > 단어를 넣으면 1줄은 무조건 겹치게 되니까
									//긴 단어의 경우 양 끝이 공백이더라도 중간에 다른 글자가 포함되어있을수 있어서 이 조건도 한번 생각해봐야합니다
									
									
									//yNum - 단어중첩구간의 y값 이 0일 경우(맨 위에 붙어있을경우) 세로로 넣어줄 단어의 맨 아래부분만 공백인지 확인하면 된다
									if (yNum - rW2LetterNum == 0 && game_table[b][yNum - rW2LetterNum + rW2.length()].get(xNum + rW1LetterNum).equals("ㅁ")) { // y가 0일때 삽입하려는 단어 맨 아래에 공백인지 확인
										
										
										//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
										for (int i = 0; i < rW2.length(); i++) {
											//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
											if(xNum + rW1LetterNum == 0) {
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
													count++;
												}
											}else if(xNum + rW1LetterNum == fullSize - 1) {
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ")) {
													count++;
												}
											}else if(xNum + rW1LetterNum > 0 && xNum + rW1LetterNum < fullSize ) {
												//양 옆이 공백란인지 확인하고 그렇지 않을 경우 count값을 올려주는 조건식입니다
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ") &&
														game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
													count++;
												}
											}else {
												
											}
										}
										
										//위의 for문이 끝나고 count값이 1일때만 양옆으로 단어가 없는 상황이기에 이때 단어를 삽입시켜줍니다
										if( count == rW2.length() - 1 ) {
											for (int i = 0; i < rW2.length(); i++) {
												game_table[b][yNum - rW2LetterNum + i].set(xNum + rW1LetterNum, rW2.substring(i, i+1));
											}
											
											diry = 0;
											dirx = 0;
											makeCount++;
											h++;
											xNum = xNum + rW1LetterNum;
											yNum = yNum - rW2LetterNum; 
											System.out.println("dirx >=2  //   case.1");	
											
											//단어, 단어의 방향 추가
											word_list[b].add(rW2);
											word_dir[b].add("y");
											xLocation[b].add(xNum);
											yLocation[b].add(yNum);
											checkWordNum++;
											wordNum[b].add(checkWordNum);
											
										}else {
											//만들수 없다고 하니 h++해버리자..
											
											h++;
										}
										
									} else if (yNum - rW2LetterNum - 1 >= 0 && yNum - rW2LetterNum + rW2.length() < fullSize) {
										
										if(game_table[b][yNum - rW2LetterNum - 1].get(xNum + rW1LetterNum).equals("ㅁ") &&
												game_table[b][yNum - rW2LetterNum + rW2.length()].get(xNum + rW1LetterNum).equals("ㅁ")) {  // 세로로 넣을 단어위치에서 아래위로 빈공간인지 체크
											//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
											for (int i = 0; i < rW2.length(); i++) {
												//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
												if(xNum + rW1LetterNum == 0) {
													if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
														count++;
													}
												}else if(xNum + rW1LetterNum == fullSize - 1) {
													if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ")) {
														count++;
													}
												}else if(xNum + rW1LetterNum > 0 && xNum + rW1LetterNum < fullSize ) {
													//양 옆이 공백란인지 확인하고 그렇지 않을 경우 count값을 올려주는 조건식입니다
													if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ") &&
															game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
														count++;
													}
												}else {
													
												}
											}
											if ( count == rW2.length() - 1 ) {
												for (int i = 0; i < rW2.length(); i++) {
													game_table[b][yNum - rW2LetterNum + i].set(xNum + rW1LetterNum, rW2.substring(i, i+1));
												}
												
												diry = 0;
												dirx = 0;
												makeCount++;
												h++;
												xNum = xNum + rW1LetterNum;
												yNum = yNum - rW2LetterNum; 
												System.out.println("dirx >=2  //  case.2");		
												
												//단어, 단어의 방향 추가
												word_list[b].add(rW2);
												word_dir[b].add("y");
												xLocation[b].add(xNum);
												yLocation[b].add(yNum);
												checkWordNum++;
												wordNum[b].add(checkWordNum);
												
											}else {
												//만들수 없다고 하니 h++해버리자..
												h++;
											}
										}else {
											//만들수 없다고 하니 h++해버리자..
											h++;
										}
									
										
									} else if (yNum - rW2LetterNum == fullSize - 1 &&
											game_table[b][yNum - rW2LetterNum - 1].get(xNum + rW1LetterNum).equals("ㅁ")) {  // 가로로 들어간 단어 시작이 맨 아래부터일 경우  ==> 세로로 넣을 단어위치에서 위가 빈 공간인지 체크
										
										//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
										for (int i = 0; i < rW2.length(); i++) {
											//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
											if(xNum + rW1LetterNum == 0) {
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
													count++;
												}
											}else if(xNum + rW1LetterNum == fullSize - 1) {
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ")) {
													count++;
												}
											}else if(xNum + rW1LetterNum > 0 && xNum + rW1LetterNum < fullSize ) {
												//양 옆이 공백란인지 확인하고 그렇지 않을 경우 count값을 올려주는 조건식입니다
												if(game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum - 1).equals("ㅁ") &&
														game_table[b][yNum - rW2LetterNum + i].get(xNum + rW1LetterNum + 1).equals("ㅁ")) {
													count++;
												}
											}else {
												
											}
										}
										if( count == rW2.length() - 1 ) {
											for (int i = 0; i < rW2.length(); i++) {
												game_table[b][yNum - rW2LetterNum + i].set(xNum + rW1LetterNum, rW2.substring(i, i+1));
											}
											diry = 0;
											dirx = 0;
											makeCount++;
											h++;
											xNum = xNum + rW1LetterNum;
											yNum = yNum - rW2LetterNum; 
											System.out.println("dirx >=2  case.3");		
											
											//단어, 단어의 방향 추가
											word_list[b].add(rW2);
											word_dir[b].add("y");
											xLocation[b].add(xNum);
											yLocation[b].add(yNum);
											checkWordNum++;
											wordNum[b].add(checkWordNum);
											
										}else {
											//만들수 없다고 하니 h++해버리자..
											h++;
										}
										
									}else {
										rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
										for (int i = 0; i < rW2.length(); i++) {
											if(rW1Letter.equals(rW2.substring(i, i+1))) {
												rW2LetterNum = i;
											}
										}
										h++;
									}
									
								}
								else {	// rW2를 새로운 단어로 교체함에 따라 rW2LetterNum도 교체해준다
									rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
									for (int i = 0; i < rW2.length(); i++) {
										if(rW1Letter.equals(rW2.substring(i, i+1))) {
											rW2LetterNum = i;
										}
									}
									h++;	//무한루프를 탈출하기 위해 여러번 시도해 본 후 답이 없는경우는 만들지 않고 탈출
								}
							}
							
							
//							 세로일 경우 입니다
							else if(diry >= 1 ) {  // diry값이 2이상일 경우를 원했으나,,, 위의 가로세로 체크 부분에서 2음절짜리 단어일 경우 가로1 세로1 이런식으로 받아오기에 세로라고 1개라도 찍힐 시 세로로 취급하기로 했다.
								// rW2의 시작부분이 0이상이어야 하고 마지막 부분이 배열의 마지막까지여야한다
								if(xNum - rW2LetterNum >= 0 && xNum + rW2.length() - rW2LetterNum <= fullSize) {
									//넣어줄 위치의 시작과 마지막부분이 비어 있는 칸인지 확인 후 넣어주자
									
//									System.out.println("이 값이 에러인가? " + (yNum - rW2LetterNum) + "yNum : " + yNum + ", rW2LetterNum : " + rW2LetterNum);
									
//									System.out.println("세로, 현재 2번째 단어 : " + rW2);
//									System.out.println("세로, 단어의 첫 음절 : " + game_table[yNum + rW1LetterNum].get(xNum - rW2LetterNum));
//									System.out.println("세로, 단어의 마지막 음절 : " + game_table[yNum + rW1LetterNum].get(xNum - rW2LetterNum + rW2.length()));
									
									//(xNum - 단어중첩구간 x값) = 0 일 경우(맨 위에 붙어있을경우) 세로로 넣어줄 단어의 맨 아래부분만 공백인지 확인하면 된다
									if (xNum - rW2LetterNum == 0 &&
											game_table[b][yNum + rW1LetterNum].get(xNum - rW2LetterNum + rW2.length()).equals("ㅁ")) {
										
										//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
										for (int i = 0; i < rW2.length(); i++) {
											//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
											if(yNum + rW1LetterNum == 0) {
												if(game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else if(yNum + rW1LetterNum == fullSize - 1) {
												if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else if(yNum + rW1LetterNum > 0 && yNum + rW1LetterNum < fullSize) {
												//양 옆이 공백란인지 확인하고 그럴 경우 count값을 올려주는 조건식입니다
												if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ") &&
														game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else {
												
											}
										}
										
										//위의 for문이 끝나고 count값이 1일때만 양옆으로 단어가 없는 상황이기에 이때 단어를 삽입시켜줍니다
										if( count == rW2.length() - 1 ) {
											for (int i = 0; i < rW2.length(); i++) {
												game_table[b][yNum + rW1LetterNum].set(xNum - rW2LetterNum + i, rW2.substring(i, i+1));
											}
											
											diry = 0;
											dirx = 0;
											makeCount++;
											h++;
											yNum = yNum + rW1LetterNum;
											xNum = xNum - rW2LetterNum;
											System.out.println("diry >=2  case.1");
											
											//단어, 단어의 방향 추가
											word_list[b].add(rW2);
											word_dir[b].add("x");
											xLocation[b].add(xNum);
											yLocation[b].add(yNum);
											checkWordNum++;
											wordNum[b].add(checkWordNum);
											
										}
										//count값이 0일 경우... 찾지 못한경우이기 때문에 탈출하도록 합니다
										else {
											rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
											for (int i = 0; i < rW2.length(); i++) {
												if(rW1Letter.equals(rW2.substring(i, i+1))) {
													rW2LetterNum = i;
												}
											}
											h++;
										}
										
									} else if (xNum + rW2LetterNum  > 0 && xNum - rW2LetterNum + rW2.length() < fullSize - 1) {
										
										if (game_table[b][yNum + rW1LetterNum].get(xNum - rW2LetterNum + rW2.length()).equals("ㅁ") &&
												game_table[b][yNum + rW1LetterNum].get(xNum + rW2LetterNum - 1).equals("ㅁ")) {  // rW2의 마지막 음절이 시작 음절일 경우
											//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
											for (int i = 0; i < rW2.length(); i++) {
												//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
												if(yNum + rW1LetterNum == 0) {
													if(game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
														count++;
													}
												}else if(yNum + rW1LetterNum == fullSize - 1) {
													if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
														count++;
													}
												}else if(yNum + rW1LetterNum > 0 && yNum + rW1LetterNum < fullSize) {
													//양 옆이 공백란인지 확인하고 그럴 경우 count값을 올려주는 조건식입니다
													if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ") &&
															game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
														count++;
													}
												}else {
													
												}
											}
											if ( count == rW2.length() - 1 ) {
												for (int i = 0; i < rW2.length(); i++) {
													game_table[b][yNum + rW1LetterNum].set(xNum - rW2LetterNum + i, rW2.substring(i, i+1));
												}												
												
												diry = 0;
												dirx = 0;
												makeCount++;
												h++;
												yNum = yNum + rW1LetterNum;
												xNum = xNum - rW2LetterNum;
												System.out.println("diry >=2  case.2");
												
												//단어, 단어의 방향 추가
												word_list[b].add(rW2);
												word_dir[b].add("x");
												xLocation[b].add(xNum);
												yLocation[b].add(yNum);
												checkWordNum++;
												wordNum[b].add(checkWordNum);
												
											}else {
												//만들수 없다고 하니 h++해버리자..
												
												h++;
											}
										}else {
											//만들수 없다고 하니 h++해버리자..
											
											h++;
										}
										

										// 3개 이상인 단어들만 따로 분리, 위에서 앞과 뒤가 겹치는 로직은 이미 실행하였고 여기로 넘어온 순간 rW2의 중간부분이 rW1Letter와 겹치는것
									} else if (xNum - rW2LetterNum == fullSize - 1 &&
											game_table[b][yNum + rW1LetterNum].get(xNum + rW2LetterNum - 1).equals("ㅁ")) {
										
										
										//들어갈 자리의 양 옆이 공백인지 확인하는 조건식 시작, rW2가 새로 들어갈 단어이기에 이 단어의 길이로 비교해야합니다.
										for (int i = 0; i < rW2.length(); i++) {
											//검사하려는 시작 위치가 0미만이 되거나 게임 사이즈를 초과하게 되면 index 오류가 나기 때문에 조건을 분할해서 넣어줍니다
											if(yNum + rW1LetterNum == 0) {
												if(game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else if(yNum + rW1LetterNum == fullSize - 1) {
												if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else if(yNum + rW1LetterNum > 0 && yNum + rW1LetterNum < fullSize) {
												//양 옆이 공백란인지 확인하고 그럴 경우 count값을 올려주는 조건식입니다
												if(game_table[b][yNum + rW1LetterNum - 1].get(xNum - rW2LetterNum + i).equals("ㅁ") &&
														game_table[b][yNum + rW1LetterNum + 1].get(xNum - rW2LetterNum + i).equals("ㅁ")) {
													count++;
												}
											}else {
												
											}
										}
										if( count == rW2.length() - 1 ) {
											for (int i = 0; i < rW2.length(); i++) {
												game_table[b][yNum + rW1LetterNum].set(xNum - rW2LetterNum + i, rW2.substring(i, i+1));
											}
											diry = 0;
											dirx = 0;
											makeCount++;
											h++;
											yNum = yNum + rW1LetterNum;
											xNum = xNum - rW2LetterNum;
											System.out.println("diry >=2  case.3");	
											
											//단어, 단어의 방향 추가
											word_list[b].add(rW2);
											word_dir[b].add("x");
											xLocation[b].add(xNum);
											yLocation[b].add(yNum);
											checkWordNum++;
											wordNum[b].add(checkWordNum);
											
										}else {
											//만들수 없다고 하니 h++해버리자..
											
											h++;
										}
										
									}else {
										rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
										for (int i = 0; i < rW2.length(); i++) {
											if(rW1Letter.equals(rW2.substring(i, i+1))) {
												rW2LetterNum = i;
											}
										}
										h++;
									}
								}
								
								else {	// rW2를 새로운 단어로 교체함에 따라 rW2LetterNum도 교체해준다
									rW2 = crossService.checkWord(rW1Letter).get(0).getWord();
									for (int i = 0; i < rW2.length(); i++) {
										if(rW1Letter.equals(rW2.substring(i, i+1))) {
											rW2LetterNum = i;
										}
									}
									h++;
								}
							}
							
							
							else { // 세로, 가로 둘다 아닐경우 => 에러 방지용, h증가 시켜서 while문 닫히게는 해야하니까...
								h++;
							}
							
							
						}  // h 종료지점 - 가로세로 여부에 따라 단어 넣어주기
						
						dirx = 0;
						diry = 0;
						
						rW1 = rW2;
						rW1Leng = rW1.length() -1;
						makeCount++;
						
					} // makeCount 종료지점
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					System.out.println("-------------------섹션 종료-------------------");
				} //섹션 종료 포인트
			}
			for (int i = 0; i < fullSize; i++) {
				System.out.println(game_table[b][i]);
			}
			
			
			System.out.println("들어간 단어 : " + word_list[b]);
			System.out.println("각 단어의 방향 : " + word_dir[b]);
			System.out.println("x좌표 : " + xLocation[b]);
			System.out.println("y좌표 : " + yLocation[b]);
			System.out.println("단어의 순번 : " + wordNum[b]);
			System.out.println();
			System.out.println();
		}
		
		
		for (int i = c; i < a + c; i++) {
			for (int j = 0; j < fullSize; j++) {
				System.out.println(game_table[i][j]);
				
			}
			
			for (int j = 0; j < wordNum[i].size(); j++) {
				cwList.setGid(i);
				cwList.setGameSize(fullSize);
				cwList.setWordNum(wordNum[i].get(j));
				cwList.setDir(word_dir[i].get(j));
				cwList.setWord(word_list[i].get(j));
				cwList.setXLocation(xLocation[i].get(j));
				cwList.setYLocation(yLocation[i].get(j));
				crossService.crossDao.addToList(cwList);
				
			}
			
			
			System.out.println("들어간 단어 : " + word_list[i]);
			System.out.println("각 단어의 방향 : " + word_dir[i]);
			System.out.println("x좌표 : " + xLocation[i]);
			System.out.println("y좌표 : " + yLocation[i]);
			System.out.println("단어의 순번 : " + wordNum[i]);
			System.out.println();
			System.out.println();
		}
		
//		crossService.addToList(cwList);
		context.close();
		
	}
	
	public static void main(String[] args) {
		DataSource datasource = new DataSource();
//		CrossDao crossDao = new CrossDao(datasource);
//		CrossServiceImpl crossServiceImpl = new CrossServiceImpl(crossDao);
//		CWList cwList = null;
		int a = 6;
		int b = 2;
		
		MakeCW makeCW;
		makeCW = new MakeCW();
		makeCW.MakeCrossWord(a, b);
		
		
		
		
		
	}
	
	

}
