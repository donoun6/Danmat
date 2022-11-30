package com.cross.Danmat.crossWord.service;

import java.util.List;

import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.domain.Word;

public interface CrossService {
	
	/**
	 * 게임판 제작 시 첫번째 단어를 호출합니다
	 * @param crossWord
	 * @return
	 */
	public List<Word> firstWord(Word crossWord);
	
	
	/**
	 * DB 내부의 모든 단어를 검색합니다
	 * @param crossWord
	 * @return
	 */
	public List<Word> allWord(Word word);
	
	
	/**
	 * 게임 제작시 사용할 단어를 랜덤으로 뽑아옵니다
	 * @param crossWord
	 * @return
	 */
	public List<Word> randomWord(Word word);

	
	/**
	 * 뽑아온 랜덤단어에서 한 음절을 랜덤으로 뽑은 후 이에 연결될 수 있는 단어를 찾기 위한 메소드입니다.
	 * @param check
	 * @return
	 */
	public List<Word> checkWord(String check);
	
	
	/**
	 * 게임판을 작성 후 게임 리스트 중 오류가 있거나 해당 게임판과는 결이 맞지 않는 자료를 삭제하기 위한 메소드입니다.
	 * @param crossword
	 */
	public void deleteList(int gid);
	
	
	/**
	 * 만들어진 게임판을 DB에 넣기 위한 메소드입니다.
	 * @param cwList
	 */
	public void addToList(Crossword crossword);
	
	
	/**
	 * 게임판을 고르기 위해 게임판의 순번인 gid 중 랜덤으로 뽑아오기 위한 메소드입니다.
	 * @param cwList
	 * @return
	 */
	public GidCommand randomGid(Crossword crossword);
	
	
	/**
	 * 위에서 뽑아온 랜덤 gid값을 토대로 동일한 gid값을 가진 단어들을 호출합니다. 게임판 작성을 위해 존재하는 메소드 입니다.
	 * @param cwList
	 * @return
	 */
	public List<Crossword> playGame(Crossword crossword);
	
	
	/**
	 * 게임판 제작 시 DB에 남아있는 gid의 제일 높은 값을 토대로 새로운 게임판의 gid값을 부여하기 위해 존재하는 메소드입니다.
	 * @param cwList
	 * @return
	 */
	public GidCommand lastCWNum(Crossword crossword);
	
	/**
	 * 게임판 생성 후 확인하며 삭제할 시 리스트를 뽑아내기 위해 중복된 gid값을 제거하고 오름차순으로 gid를 정리하기 위한 메소드입니다.
	 * @param cwList
	 * @return
	 */
	public List<GidCommand> allGid();
	
	/**
	 * 게임판 생성후 확인 또는 저장되어있는 게임판을 gid값으로 가져오기 위한 메소드입니다.
	 * @param cwList
	 * @return
	 */
	public List<Crossword> findByGid(Crossword crossword);
	
	/**
	 * 해당 gid값으로 List의 길이가 얼마정도인지를 알아내기 위한 메소드입니다. .length값으로 사용하시면 됩니다.
	 * @param cwList
	 * @return
	 */
	public int wordNumByGid(Crossword crossword);
	
	
	
}
