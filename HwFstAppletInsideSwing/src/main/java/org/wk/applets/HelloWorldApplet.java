package org.wk.applets;

import java.awt.Container;

import javax.swing.JApplet;

import org.wk.panels.HelloPanel;

public class HelloWorldApplet extends JApplet{


	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() {

		try {
	        javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
	            public void run() {
	                createGUI();
	            }
	        });
	    } catch (Exception e) {
	        System.err.println("createGUI didn't successfully complete");
	    }
	}
	
	private void createGUI() {
		
		Container content = getContentPane();
		new HelloPanel(content);
		
	}

}
