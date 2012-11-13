package pl.kwisniewski.main;

import pl.kwisniewski.frames.HelloWorldFrame;
import pl.kwisniewski.panels.HelloPanel;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		HelloWorldFrame helloWorldFrame = new HelloWorldFrame();
		HelloPanel helloPanel = new HelloPanel(helloWorldFrame, "");
		
	}

}
