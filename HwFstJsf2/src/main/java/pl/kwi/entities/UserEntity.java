package pl.kwi.entities;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "userEntity")
public class UserEntity {
	
	
	private String name;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		

}
