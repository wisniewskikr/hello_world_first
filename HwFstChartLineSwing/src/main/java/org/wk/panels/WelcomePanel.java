package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.wk.chart.line.entities.LineChartContent;
import org.wk.chart.line.entities.LineChartLine;
import org.wk.chart.line.entities.LineChartPoint;
import org.wk.services.LineChartService;
import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;
	private LineChartService lineChartService;
	private String name;
	
	
	public WelcomePanel(JFrame frame, String name){
		super(frame);
		this.name = name;
		
		propertiesService = new PropertiesService();
		lineChartService = new LineChartService();
				
		setUpLayout();
		frame.setContentPane(this);	
		frame.setVisible(true);
		
	}
	
	private void setUpLayout() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getResponsePanel(), BorderLayout.CENTER);
		this.add(getChartPanel(), BorderLayout.CENTER);
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
	
	private JPanel getResponsePanel(){
		
		panel = new JPanel();
		panel.add(new JLabel("Hello World: " + name));
		return panel;
		
	}
	
	private JPanel getChartPanel() {
		
		List<LineChartPoint> points = new ArrayList<LineChartPoint>();
		
		try {
			
			char[] charArray = name.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				String xPoint = String.valueOf(charArray[i]);
				double yPoint = Double.valueOf(xPoint);
				points.add(new LineChartPoint(xPoint, yPoint));
			}
			
		} catch (Exception e) {
			return new JPanel();
		}
		
		List<LineChartLine> lines = new ArrayList<LineChartLine>();
		LineChartLine line = new LineChartLine("Test line", points);
		lines.add(line);
		
		LineChartContent content = new LineChartContent("Test chart", "X Axis", "Y Axis", lines);
		
		JFreeChart freeChart = lineChartService.createLineChart(content);
		
		ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		return chartPanel;
		
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
	
}
