package org.wk.swing.abstrs;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstrPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected JPanel panel;
	protected JTextField textField;
	
	public AbstrPanel(JFrame frame){
		this.frame = frame;
	}

}
