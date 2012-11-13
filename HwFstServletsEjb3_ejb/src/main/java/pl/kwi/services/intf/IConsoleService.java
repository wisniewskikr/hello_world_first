package pl.kwi.services.intf;

import javax.ejb.Local;

@Local
public interface IConsoleService {
	
	/**
	 * Method displays text 'Hello World' and name on console.
	 * 
	 * @param name object String with name value
	 */
	void displayHelloWorld(String name);
	

}
