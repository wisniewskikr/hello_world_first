package org.wk.mains;

import javax.swing.JFrame;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.wk.frames.HelloWorldFrame;
import org.wk.panels.HelloPanel;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new HelloWorldFrame();
		
		WeldContainer weld = new Weld().initialize();
		HelloPanel helloPanel = weld.instance().select(HelloPanel.class).get();
		helloPanel.display(frame);
		
	}

}
