package pl.kwi.services.impl;

import javax.ejb.Stateless;

import pl.kwi.services.intf.IConsoleService;


@Stateless
public class ConsoleService implements IConsoleService{

	
	/* (non-Javadoc)
	 * @see pl.kwi.services.intf.IConsoleService#displayHelloWorld(java.lang.String)
	 */
	public void displayHelloWorld(String name){
		System.out.println("Hello World: " + name);
	}
	

}
