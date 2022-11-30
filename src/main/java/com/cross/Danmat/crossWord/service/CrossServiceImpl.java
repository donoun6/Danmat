package com.cross.Danmat.crossWord.service;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.cross.Danmat.crossWord.dao.CrossDao;

import com.cross.Danmat.crossWord.command.GidCommand;
import com.cross.Danmat.crossWord.domain.Crossword;
import com.cross.Danmat.crossWord.domain.Word;

public class CrossServiceImpl implements CrossService {
	DataSource datasource = new DataSource();
	CrossDao crossDao = new CrossDao(datasource);
	
	MakeCW makeCW= null;
	
	public CrossServiceImpl() {
		super();
	}
	
	public CrossServiceImpl(CrossDao crossDao) {
		this.crossDao = crossDao;
	}
	
	public List<Word> firstWord(Word crossWord) {
		return crossDao.firstWord(crossWord);
	}
	
	public List<Word> allWord(Word crossWord) {
		return crossDao.allWord(crossWord);
	}
	
	public List<Word> randomWord(Word crossWord) {
		return crossDao.randomWord(crossWord);
	}

	public List<Word> checkWord(String check) {
		return crossDao.checkWord(check);
	}
	
	public void deleteList(int gid) {
		this.crossDao.deleteList(gid);
	}
	
	public void addToList(Crossword cwList) {
		this.crossDao.addToList(cwList);
	}
	
	public GidCommand randomGid(Crossword cwList) {
		return this.crossDao.randomGid(cwList);
	}
	
	public List<Crossword> playGame(Crossword cwList) {
		return this.crossDao.playGame(cwList);
	}
	
	public GidCommand lastCWNum(Crossword cwList) {
		return this.crossDao.lastCWNum(cwList);
	}
	public void makeCrossWord (int size, int repeatNum) {
		this.makeCrossWord(size, repeatNum);
	}
	
	public List<GidCommand> allGid() {
		return this.crossDao.allGid();
	}
	
	public List<Crossword> findByGid(Crossword cwList) {
		return crossDao.findByGid(cwList);
	}
	
	public int wordNumByGid(Crossword cwList) {
		return crossDao.wordNumByGid(cwList).size();
	}
	

}
