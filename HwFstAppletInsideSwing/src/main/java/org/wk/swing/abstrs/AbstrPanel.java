package org.wk.swing.abstrs;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstrPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected Container container;
	protected JPanel panel;
	protected JTextField textField;
	
	public AbstrPanel(Container container){
		this.container = container;
	}

}
