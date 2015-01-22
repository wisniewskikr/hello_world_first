package pl.kwi.entities;

import pl.kwi.db.spring.AbstractEntity;

public class UserEntity extends AbstractEntity{
	
	
	private static final long serialVersionUID = 1L;	
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
