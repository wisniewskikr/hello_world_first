package pl.kwi.midlet;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import pl.kwi.screens.HelloScreen;

public class HelloWorldMidlet extends MIDlet {
	
	
	private static HelloWorldMidlet midlet;

	
	public HelloWorldMidlet() {
		
		midlet = this;
		new HelloScreen();
	}
		

	public static HelloWorldMidlet getMidlet() {
		return midlet;
	}


	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
	}

}
