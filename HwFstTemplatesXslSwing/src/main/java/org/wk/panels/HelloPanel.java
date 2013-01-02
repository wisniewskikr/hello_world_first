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
import javax.swing.JTextField;

import org.wk.jaxb.entities.XslData;
import org.wk.services.XmlService;
import org.wk.services.XslService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private XmlService xmlService;
	private XslService xslService;

	
	public HelloPanel(JFrame frame){
		super(frame);
		xmlService = new XmlService();
		xslService = new XslService();
		
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
			
			String name = textField.getText();
			
			XslData xslData = new  XslData();
			xslData.setUserName(name);
			xmlService.writeObjectToFile(xslData, XslData.class, new File(XmlService.FILE_PATH + XmlService.XML_INPUT_FILE));
			
			File inputXml = new File(XmlService.FILE_PATH + XmlService.XML_INPUT_FILE);
			String templatePath = XslService.TEMPLATE_PATH + XslService.TEMPLATE_FILE;
			File outputFile = new File(XmlService.FILE_PATH + XmlService.XML_OUTPUT_FILE);
			xslService.transformTemplate(inputXml, templatePath, outputFile);
			
			new WelcomePanel(frame);
			
		}
		
	}
	

		
}
