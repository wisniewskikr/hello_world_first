package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wk.services.FileService;
import org.wk.services.ITextService;
import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;
	private FileService fileService;
	private ITextService iTextService;
	private File inputFile = null;
	
	
	public WelcomePanel(JFrame frame, String name){
		super(frame);
		
		propertiesService = new PropertiesService();
		fileService = new FileService();
		iTextService = new ITextService();
		
		inputFile = new File(ITextService.FILE_PATH + ITextService.FILE_NAME);
		iTextService.createHelloWorldPdf(name, inputFile);
				
		setUpLayout();
		frame.setContentPane(this);	
		frame.setVisible(true);
		
	}
	
	private void setUpLayout() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getDownloadMessagePanel(), BorderLayout.CENTER);
		this.add(getDownloadButtonPanel(), BorderLayout.CENTER);
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
		panel.add(new JLabel("Page: Welcome"));
		return panel;
		
	}
	
	private JPanel getDownloadMessagePanel(){
		
		panel = new JPanel();
		String message = "File to download: " + inputFile.getName();
		panel.add(new JLabel(message));
		return panel;
		
	}
	
	private JPanel getDownloadButtonPanel(){
		
		panel = new JPanel();		
		JButton buttonDownload = new JButton("Download");
		buttonDownload.addActionListener(new ActionListenerButtonDownload(this));
		panel.add(buttonDownload);
		return panel;
		
	}
	
	private JPanel getDescriptionPanel(){
		
		panel = new JPanel();
		
		// Descripton Row 2
		String descriptionRow1Prop = propertiesService.getProjectProp(PropertiesService.PROP_DESCRIPTION_WELCOME_ROW_1);
		String descriptionRow1Message = MessageFormat.format(descriptionRow1Prop, (Object)null);
		panel.add(new JLabel(descriptionRow1Message));
		
		// Descripton Row 2
		String descriptionRow2Prop = propertiesService.getProjectProp(PropertiesService.PROP_DESCRIPTION_WELCOME_ROW_2);
		String descriptionRow2Message = MessageFormat.format(descriptionRow2Prop, (Object)null);
		panel.add(new JLabel(descriptionRow2Message));
		
		return panel;
		
	}
	
	private JPanel getButtonPanel(){
		
		panel = new JPanel();
		JButton jButtonOK = new JButton("Back");
		jButtonOK.addActionListener(new ActionListenerButtonOK());
		panel.add(jButtonOK);
		return panel;
		
	}
	
	private class ActionListenerButtonDownload implements ActionListener{

		private Component parent;
						
		public ActionListenerButtonDownload(Component parent) {
			this.parent = parent;			
		}		
		
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			jfc.setSelectedFile(inputFile);
			panel.add(jfc);
			int option = jfc.showSaveDialog(parent);
			if(option == JFileChooser.APPROVE_OPTION){ 
				File outputFile = jfc.getSelectedFile();
				fileService.copyFileToFile(inputFile, outputFile, new byte[1024]);
			}
			if(option == JFileChooser.CANCEL_OPTION){ 
			}	
		}
		
	}
	
	private class ActionListenerButtonOK implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			new HelloPanel(frame);
		}
		
	}
	
}
