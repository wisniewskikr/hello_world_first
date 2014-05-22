package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.wk.services.CsvService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private CsvService csvService;
	
	
	public WelcomePanel(JFrame frame){
		super(frame);
		csvService = new CsvService();
		
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
		panel.add(new JLabel(CsvService.FILE_PATH + CsvService.FILE_NAME));
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
		
		String fileLocation = CsvService.FILE_PATH + CsvService.FILE_NAME;
		File file = new File(fileLocation);
		List<String[]> lines = csvService.readLinesFromFile(file);
		return lines.get(0)[0];
		
	}
	
}
