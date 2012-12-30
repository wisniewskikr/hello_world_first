package pl.kwi.services;

public class ConsoleService {

	
	/**
	 * Method displays text 'Hello World' and name on console.
	 * 
	 * @param name object String with name value
	 */
	public void displayHelloWorld(String name){
		String message = getHelloWorldMessage(name);
		System.out.println(message);
	}
	
	/**
	 * Method returns message with text 'Hello World' and name.
	 * 
	 * @param name object String with name
	 * @return object String with message with text 'Hello World' and name
	 */
	public String getHelloWorldMessage(String name) {
		return "Hello World: " + name;
	}
	

}
