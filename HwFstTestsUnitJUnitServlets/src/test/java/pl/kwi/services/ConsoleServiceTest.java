package pl.kwi.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConsoleServiceTest {
	
	private ConsoleService service;
	
	@Before
	public void setUp(){
		service = new ConsoleService();
	}

	@Test
	public void getHelloWorldMessage_name_chris() {
		String name = "Chris";
		String message = service.getHelloWorldMessage(name);
		assertEquals("Hello World: Chris", message);
	}
	
	@Test
	public void getHelloWorldMessage_name_jack() {
		String name = "Jack";
		String message = service.getHelloWorldMessage(name);
		assertEquals("Hello World: Jack", message);
	}

}
