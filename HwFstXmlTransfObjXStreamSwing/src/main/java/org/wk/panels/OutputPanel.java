package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wk.services.Service;
import org.wk.swing.abstrs.AbstrPanel;

public class OutputPanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private Service service;
	
	
	public OutputPanel(JFrame frame){
		super(frame);
		service = new Service();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getResponsePanel(), BorderLayout.CENTER);
		this.add(getFilePanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		
		frame.setContentPane(this);	
		frame.setVisible(true);
		
	}
	
	private JPanel getTitlePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Hello World"));
		return panel;
		
	}
	
	private JPanel getSubtitlePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Page: Welcome"));
		return panel;
		
	}
	
	private JPanel getResponsePanel(){
		
		String name = service.loadName();		
		panel = new JPanel();
		panel.add(new JLabel("Hello World: " + name));
		return panel;
		
	}
	
	private JPanel getFilePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Your name was taken from file:"));
		panel.add(new JLabel(Service.FILE_PATH + Service.FILE_NAME));
		return panel;
		
	}
	
	private JPanel getButtonPanel(){
		
		panel = new JPanel();
		JButton jButtonOK = new JButton("Back");
		jButtonOK.addActionListener(new ActionListenerButtonOK());
		panel.add(jButtonOK);
		return panel;
		
	}
	
	private class ActionListenerButtonOK implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			new InputPanel(frame);
		}
		
	}

	
}
