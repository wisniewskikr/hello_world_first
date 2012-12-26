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
import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;


public class HelloPanel extends AbstrPanel{
		

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;
	private File file;
	private JPanel uploadMessagePanel;
	
	public HelloPanel(JFrame frame){
		super(frame);
		
		propertiesService = new PropertiesService();
		uploadMessagePanel = new JPanel();
				
		setUpLayout();
		frame.setContentPane(this);	
		frame.setVisible(true);
		
	}
	
	private void setUpLayout() {
		
		handleUploadMessagePanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(uploadMessagePanel, BorderLayout.CENTER);
		this.add(getUploadButtonPanel(), BorderLayout.CENTER);
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
	
	private void handleUploadMessagePanel(){
		
		String fileName;
		if(file != null) {
			fileName = file.getName();
		}else {
			fileName = "";
		}
		
		String message = "File to upload: " + fileName;
		uploadMessagePanel.add(new JLabel(message));
		
	}
	
	private JPanel getUploadButtonPanel(){
		
		panel = new JPanel();		
		JButton buttonDownload = new JButton("Upload");
		buttonDownload.addActionListener(new ActionListenerButtonUpload(this));
		panel.add(buttonDownload);
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
	
	private class ActionListenerButtonUpload implements ActionListener{

		private Component parent;
				
		public ActionListenerButtonUpload(Component parent) {
			this.parent = parent;
		}		
		
		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
			panel.add(jfc);
			int option = jfc.showOpenDialog(parent);
			if(option == JFileChooser.APPROVE_OPTION){ 
				file = jfc.getSelectedFile();
				
				uploadMessagePanel.removeAll();
				handleUploadMessagePanel();
				uploadMessagePanel.revalidate();
				uploadMessagePanel.repaint();
				
				
			}
			if(option == JFileChooser.CANCEL_OPTION){ 
				file = null;
			}	
		}
		
	}
	
	private class ActionListenerButtonOK implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			new WelcomePanel(frame, file);	
		}
		
	}
	
}
