package pl.kwi.commands;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class with data of page "Input".
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class InputCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Please fill this field")
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
