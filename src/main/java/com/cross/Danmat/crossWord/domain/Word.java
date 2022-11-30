package com.cross.Danmat.crossWord.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Word {
	private int wid;
	private String word;
	private int len;
	private int grade;
	private String category;
	private String category2;
	private String def;
	private String ex;
	
	public Word() {
		super();
	}

	@Override
	public String toString() {
		return "CrossWord [wid=" + wid + ", word=" + word + ", len=" + len + ", grade=" + grade + ", category="
				+ category + ", category2=" + category2 + ", def=" + def + ", ex=" + ex + "]";
	}
	
	
}
