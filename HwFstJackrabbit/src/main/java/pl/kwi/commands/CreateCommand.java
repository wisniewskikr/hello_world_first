package pl.kwi.commands;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateCommand implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Please fill this field")
	private String name;
	private Long id;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
}
