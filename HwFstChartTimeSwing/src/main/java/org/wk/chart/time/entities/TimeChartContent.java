package org.wk.chart.time.entities;

import java.util.ArrayList;
import java.util.List;

public class TimeChartContent {
	
	
	private String chartTitle;
	private String xAxisTitle;
	private String yAxisTitle;
	private List<TimeChartLine> lines = new ArrayList<TimeChartLine>();
		
	
	public TimeChartContent(String chartTitle, String xAxisTitle,
			String yAxisTitle, List<TimeChartLine> lines) {
		this.chartTitle = chartTitle;
		this.xAxisTitle = xAxisTitle;
		this.yAxisTitle = yAxisTitle;
		this.lines = lines;
	}
	
	
	public String getChartTitle() {
		return chartTitle;
	}
	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}
	
	public String getxAxisTitle() {
		return xAxisTitle;
	}
	public void setxAxisTitle(String xAxisTitle) {
		this.xAxisTitle = xAxisTitle;
	}
	
	public String getyAxisTitle() {
		return yAxisTitle;
	}
	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}
	
	public List<TimeChartLine> getLines() {
		return lines;
	}
	public void setLines(List<TimeChartLine> lines) {
		this.lines = lines;
	}
	

}
