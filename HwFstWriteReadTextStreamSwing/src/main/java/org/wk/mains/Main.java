package org.wk.mains;

import javax.swing.JFrame;

import org.wk.frames.HelloWorldFrame;
import org.wk.panels.HelloPanel;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new HelloWorldFrame();
		new HelloPanel(frame);
		
	}

}
