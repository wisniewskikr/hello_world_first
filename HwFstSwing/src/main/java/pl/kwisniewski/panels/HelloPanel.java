package pl.kwisniewski.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HelloPanel extends JPanel{
	
	private JTextField jTextField;
	private JFrame frame;
	
	public HelloPanel(JFrame frame, String name){
		
		this.frame = frame;
		frame.setContentPane(this);	
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getRequestPanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
	}
	
	private JPanel getTitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Hello World - Swing"));
		return jPanel;
		
	}
	
	private JPanel getSubtitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Page: Hello"));
		return jPanel;
		
	}
	
	private JPanel getRequestPanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Type your name: "));
		jTextField = new JTextField(10);
		jPanel.add(jTextField);
		return jPanel;
		
	}
	
	private JPanel getButtonPanel(){
		
		JPanel jPanel = new JPanel();
		JButton jButtonOK = new JButton("OK");
		jButtonOK.addActionListener(new ActionListenerButtonOK());
		jPanel.add(jButtonOK);
		return jPanel;
		
	}
	
	private class ActionListenerButtonOK implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String name = jTextField.getText();
			new WelcomePanel(frame, name);	
		}
		
	}
	
}
