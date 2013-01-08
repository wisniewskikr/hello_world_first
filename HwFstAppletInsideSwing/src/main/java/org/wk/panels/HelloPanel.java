package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;

	
	public HelloPanel(Container container){
		super(container);
		
		propertiesService = new PropertiesService();
				
		setUpLayout();
		container.setVisible(true);
		
	}
	
	private void setUpLayout() {
		
		container.removeAll();	
		
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.add(getTitlePanel(), BorderLayout.NORTH);
		container.add(getSubtitlePanel(), BorderLayout.NORTH);
		container.add(getRequestPanel(), BorderLayout.CENTER);
		container.add(getDescriptionPanel(), BorderLayout.CENTER);
		container.add(getButtonPanel(), BorderLayout.SOUTH);
		
		container.revalidate();
		container.repaint();
		
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
			
			System.out.println("------------- HERE");
			
			String name = textField.getText();
			new WelcomePanel(container, name);	
		}
		
	}
	
}
