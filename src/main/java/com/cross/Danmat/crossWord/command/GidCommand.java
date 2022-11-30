package com.cross.Danmat.crossWord.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GidCommand {
	
	public GidCommand() {
		super();
	}
	
	private int takeGid;

	@Override
	public String toString() {
		return "GidCommand [takeGid=" + takeGid + "]";
	}
	
	
	
	
}
