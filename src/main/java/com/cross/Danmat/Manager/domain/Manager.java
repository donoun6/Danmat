package com.cross.Danmat.Manager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Manager {
	private String mgid;
	private String mgpasswd;
	
	public Manager() {
		super();
	}
}
