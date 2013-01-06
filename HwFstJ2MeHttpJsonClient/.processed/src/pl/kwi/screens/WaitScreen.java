package pl.kwi.screens;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import pl.kwi.midlet.HelloWorldMidlet;

public class WaitScreen {
	
	
	private HelloWorldMidlet midlet;
	

	public WaitScreen() {
		
		midlet = HelloWorldMidlet.getMidlet();		
		Form form = new Form("Wait...");
				
		Display display = Display.getDisplay(midlet);
		display.setCurrent(form);
		
	}

	
}
