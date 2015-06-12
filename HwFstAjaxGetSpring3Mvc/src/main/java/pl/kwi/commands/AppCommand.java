package pl.kwi.commands;

import java.io.Serializable;

/**
 * Class with data of application.
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class AppCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
