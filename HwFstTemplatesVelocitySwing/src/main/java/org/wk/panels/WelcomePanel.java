package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wk.jaxb.entities.UserEntity;
import org.wk.services.XmlService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private XmlService xmlService;
	
	
	public WelcomePanel(JFrame frame){
		super(frame);
		xmlService = new XmlService();
		
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
		
		String name = readNameFromXmlFile();		
		panel = new JPanel();
		panel.add(new JLabel("Hello World: " + name));
		return panel;
		
	}
	
	private JPanel getFilePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("The name was taken from file: " + XmlService.XML_FILE));
		panel.add(new JLabel("from location: " + XmlService.FILE_PATH));
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
			new HelloPanel(frame);
		}
		
	}
	
	private String readNameFromXmlFile(){
		
		String fileLocation = XmlService.FILE_PATH + XmlService.XML_FILE;
		File file = new File(fileLocation);
		UserEntity user = (UserEntity) xmlService.readObjectFromFile(UserEntity.class, file);
		return user.getName();
		
	}
	
}
