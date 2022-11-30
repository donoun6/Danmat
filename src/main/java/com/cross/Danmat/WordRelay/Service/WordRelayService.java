package com.cross.Danmat.WordRelay.Service;

import java.util.List;

import com.cross.Danmat.WordRelay.Command.PointCommand;
import com.cross.Danmat.WordRelay.Command.UsedCommand;
import com.cross.Danmat.WordRelay.Command.WordCommand;
import com.cross.Danmat.WordRelay.Domain.Word;

public interface WordRelayService {
	
	public List<Word> findAll();
	
	public List<Word> randomWord();
	
	public int checkWord(String word);
	
	public List<Word> randomSplWord(String word);
	
	public void isuse(String word);
	
	public int usedWord(String word);
	
	public void delete ();
	
	public void answer(String word);
	
	public void delete2 ();
	
	public List<WordCommand> findAnswer();
	
	public void point(int point);
	
	public List<PointCommand> addPoint();
	
	public void delete3 ();
	
	public List<UsedCommand> findUsed();
	
}
