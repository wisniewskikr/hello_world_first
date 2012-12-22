package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wk.services.MailService;
import org.wk.services.TextService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private TextService textService;
	private MailService mailService;

	
	public HelloPanel(JFrame frame){
		super(frame);
		
		textService = new TextService();
		mailService = new MailService();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getRequestPanel(), BorderLayout.CENTER);
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
		panel.add(new JLabel("Page: Hello"));
		return panel;
		
	}
	
	private JPanel getRequestPanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Type your name: "));
		textField = new JTextField(10);
		panel.add(textField);
		return panel;
		
	}
	
	private JPanel getButtonPanel(){
		
		panel = new JPanel();
		JButton jButtonOK = new JButton("OK");
		jButtonOK.addActionListener(new ActionListenerButtonOK());
		panel.add(jButtonOK);
		return panel;
		
	}
	
	private class ActionListenerButtonOK implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			List<File> attachments = new ArrayList<File>();
			String name = textField.getText();			
			String fileLocation = TextService.FILE_PATH + TextService.FILE_NAME;
			File file = new File(fileLocation);
			textService.writeTextToFile(name, file);
			attachments.add(file);
			
			mailService.sendMailwithAttachement(MailService.MAIL_FROM, MailService.MAIL_TO, MailService.MAIL_SUBJECT, MailService.MAIL_SUBJECT, attachments);
			new WelcomePanel(frame);	
		}
		
	}
	
}
