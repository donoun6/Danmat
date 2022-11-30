package com.cross.Danmat.WordRelay.Service;

import java.util.List;

import com.cross.Danmat.WordRelay.Command.PointCommand;
import com.cross.Danmat.WordRelay.Command.UsedCommand;
import com.cross.Danmat.WordRelay.Command.WordCommand;
import com.cross.Danmat.WordRelay.Dao.WordRelayDao;
import com.cross.Danmat.WordRelay.Domain.Word;

public class WordRelayServiceImpl implements WordRelayService {
	WordRelayDao wordRelayDao = null;
	
	public WordRelayServiceImpl(WordRelayDao wordRelayDao) {
		this.wordRelayDao = wordRelayDao;
	}

	@Override
	public List<Word> findAll() {
		return wordRelayDao.findAll();
	}

	@Override
	public List<Word> randomWord() {
		return wordRelayDao.randomWord();
	}
	
	@Override
	public int checkWord(String word) {
		return wordRelayDao.checkWord(word);
	}
		
	@Override
	public List<Word> randomSplWord(String word) {
		return wordRelayDao.randomSplWord(word);
	}

	@Override
	public void isuse(String word) {
		wordRelayDao.isuse(word);
	}

	@Override
	public int usedWord(String word) {
		return wordRelayDao.usedWord(word);
	}

	@Override
	public void delete() {
		wordRelayDao.delete();
	}

	@Override
	public void answer(String word) {
		wordRelayDao.answer(word);
	}

	@Override
	public void delete2() {
		wordRelayDao.delete2();
	}

	@Override
	public List<WordCommand> findAnswer() {
		return wordRelayDao.findAnswer();
	}

	@Override
	public void point(int point) {
		wordRelayDao.point(point);;
		
	}

	@Override
	public List<PointCommand> addPoint() {
		return wordRelayDao.addPoint();
	}

	@Override
	public void delete3() {
		wordRelayDao.delete3();
	}

	@Override
	public List<UsedCommand> findUsed() {
		return wordRelayDao.findUsed();
	}




	
	
}
