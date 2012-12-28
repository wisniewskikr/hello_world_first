package org.wk.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.wk.chart.time.entities.TimeChartContent;
import org.wk.chart.time.entities.TimeChartLine;
import org.wk.chart.time.entities.TimeChartPoint;
import org.wk.services.TimeChartService;
import org.wk.services.PropertiesService;
import org.wk.swing.abstrs.AbstrPanel;

public class WelcomePanel extends AbstrPanel{
	

	private static final long serialVersionUID = 1L;
	private PropertiesService propertiesService;
	private TimeChartService timeChartService;
	private String name;
	
	
	public WelcomePanel(JFrame frame, String name){
		super(frame);
		this.name = name;
		
		propertiesService = new PropertiesService();
		timeChartService = new TimeChartService();
				
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
		
		List<TimeChartPoint> points = new ArrayList<TimeChartPoint>();
		
		try {
			
			char[] charArray = name.toCharArray();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2012);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			for (int i = 0; i < charArray.length; i++) {
				RegularTimePeriod xPoint = new Day(cal.getTime());
				cal.add(Calendar.DAY_OF_MONTH, 1);
				double yPoint = Double.valueOf(String.valueOf(charArray[i]));
				points.add(new TimeChartPoint(xPoint, yPoint));
			}
			
		} catch (Exception e) {
			return new JPanel();
		}
		
		List<TimeChartLine> lines = new ArrayList<TimeChartLine>();
		TimeChartLine line = new TimeChartLine("Test line", points);
		lines.add(line);
		
		TimeChartContent content = new TimeChartContent("Test chart", "X Axis", "Y Axis", lines);
		
		JFreeChart freeChart = timeChartService.createTimeChart(content);
		
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
