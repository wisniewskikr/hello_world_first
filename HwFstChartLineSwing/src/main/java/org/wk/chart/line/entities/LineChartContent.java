package org.wk.chart.line.entities;

import java.util.ArrayList;
import java.util.List;

public class LineChartContent {
	
	
	private String chartTitle;
	private String xAxisTitle;
	private String yAxisTitle;
	private List<LineChartLine> lines = new ArrayList<LineChartLine>();
		
	
	public LineChartContent(String chartTitle, String xAxisTitle,
			String yAxisTitle, List<LineChartLine> lines) {
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
	
	public List<LineChartLine> getLines() {
		return lines;
	}
	public void setLines(List<LineChartLine> lines) {
		this.lines = lines;
	}
	

}
