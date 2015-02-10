package pl.kwi.commands;

import java.io.Serializable;

/**
 * Class with data of page "Output".
 * 
 * @author Krzysztof Wisniewski
 *
 */
public class OutputCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
