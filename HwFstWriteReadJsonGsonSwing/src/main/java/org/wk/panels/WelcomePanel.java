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

import org.wk.entities.UserEntity;
import org.wk.services.JsonService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private JsonService jsonService;
	
	
	public WelcomePanel(JFrame frame){
		super(frame);
		jsonService = new JsonService();
		
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
		
		String name = readNameFromFile();		
		panel = new JPanel();
		panel.add(new JLabel("Hello World: " + name));
		return panel;
		
	}
	
	private JPanel getFilePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Your name is taken from file:"));
		panel.add(new JLabel(JsonService.FILE_PATH + JsonService.FILE_NAME));
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
	
	private String readNameFromFile(){
		
		String fileLocation = JsonService.FILE_PATH + JsonService.FILE_NAME;
		File file = new File(fileLocation);
		UserEntity user = (UserEntity)jsonService.readObjectFromJsonFile(file, UserEntity.class);
		return user.getName();
		
	}
	
}
