package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;

	
	public HelloPanel(JFrame frame){
		super(frame);
		
		propertiesService = new PropertiesService();
				
		setUpLayout();
		frame.setContentPane(this);	
		frame.setVisible(true);
		
	}
	
	private void setUpLayout() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getRequestPanel(), BorderLayout.CENTER);
		this.add(getDescriptionPanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		
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
		panel.add(new JLabel("Type digits: "));
		textField = new JTextField(10);
		panel.add(textField);
		return panel;
		
	}
	
	private JPanel getDescriptionPanel(){
		
		panel = new JPanel();
		
		// Descripton Row 1
		String descriptionRow1Prop = propertiesService.getProjectProp(PropertiesService.PROP_DESCRIPTION_HELLO_ROW_1);
		String descriptionRow1Message = MessageFormat.format(descriptionRow1Prop, (Object)null);
		panel.add(new JLabel(descriptionRow1Message));
		
		// Descripton Row 2
		String descriptionRow2Prop = propertiesService.getProjectProp(PropertiesService.PROP_DESCRIPTION_HELLO_ROW_2);
		String descriptionRow2Message = MessageFormat.format(descriptionRow2Prop, (Object)null);
		panel.add(new JLabel(descriptionRow2Message));
		
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
			String name = textField.getText();
			new WelcomePanel(frame, name);	
		}
		
	}
	
}
