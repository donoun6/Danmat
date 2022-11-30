package com.cross.Danmat.crossWord.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Crossword {
	private int gid;
	private int gameSize;
	private int wordNum;
	private String dir;
	private String word;
	private int xLocation;
	private int yLocation;
	
	public Crossword() {
		super();
	}

	@Override
	public String toString() {
		return "CWList [gid=" + gid + ", gameSize=" + gameSize + ", wordNum=" + wordNum + ", dir=" + dir + ", word="
				+ word + ", xLocation=" + xLocation + ", yLocation=" + yLocation + "]";
	}
	
	
}
