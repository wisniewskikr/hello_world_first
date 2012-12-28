package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.wk.jaxb.entities.UserEntity;
import org.wk.jaxb.entities.XslData;
import org.wk.services.StreamService;
import org.wk.services.XmlService;
import org.wk.services.XslXalanService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private XmlService xmlService;
	private StreamService streamService;
	private XslXalanService xslService;

	
	public HelloPanel(JFrame frame){
		super(frame);
		xmlService = new XmlService();
		streamService = new StreamService();
		xslService = new XslXalanService();
		
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
			transformXsl(name);		
			
			new WelcomePanel(frame);
			
		}
		
	}
	
	private void writeNameToXmlFileInTmpFolder(String name){
		
		String fileLocation = XmlService.FILE_PATH + XmlService.XSL_DATA_FILE_NAME;
		File file = new File(fileLocation);
		xmlService.writeObjectToFile(new XslData(name), XslData.class, file);
		
	}
	
	private void copyXslFileToTmpFolder() {

		try {

			InputStream is = HelloPanel.class.getResourceAsStream("/" + StreamService.XSL_FILE_NAME);
			OutputStream os = new FileOutputStream(new File(StreamService.FILE_PATH + StreamService.XSL_FILE_NAME));

			streamService.writeStreamInputToOuptpu(is, os, new byte[1024]);

			is.close();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void transformXsl(String name) {
		
		writeNameToXmlFileInTmpFolder(name);
		copyXslFileToTmpFolder();
		
		String fileLocation = null; 
		fileLocation = XmlService.FILE_PATH + XmlService.XSL_DATA_FILE_NAME;
		File xmlData = new File(fileLocation);
		
		fileLocation = StreamService.FILE_PATH + StreamService.XSL_FILE_NAME;
		File xslTemplate = new File(fileLocation);
		
		fileLocation = XmlService.FILE_PATH + XmlService.XML_RESULT_FILE_NAME;
		File result = new File(fileLocation);
		
		xslService.transformXsl(xmlData, xslTemplate, result);
		
	}
		
}
