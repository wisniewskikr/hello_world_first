package org.wk.entities;

import java.io.Serializable;

public class UserEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String name;

	
	public UserEntity() {
	}
	
	public UserEntity(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
