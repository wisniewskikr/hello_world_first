package pl.kwi.services;

import javax.faces.bean.ManagedBean;


@ManagedBean(name = "consoleService")
public class ConsoleService {

	
	/**
	 * Method displays text 'Hello World' and name on console.
	 * 
	 * @param name object String with name value
	 */
	public void displayHelloWorld(String name){
		System.out.println("Hello World: " + name);
	}
	

}
