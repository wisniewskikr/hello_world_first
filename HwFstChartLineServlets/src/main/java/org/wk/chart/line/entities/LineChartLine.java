package org.wk.chart.line.entities;

import java.util.ArrayList;
import java.util.List;

public class LineChartLine {
	
	
	private String lineTitle;
	private List<LineChartPoint> points = new ArrayList<LineChartPoint>();
		
	
	public LineChartLine(String lineTitle, List<LineChartPoint> points) {
		this.lineTitle = lineTitle;
		this.points = points;
	}
	
	
	public String getLineTitle() {
		return lineTitle;
	}
	public void setLineTitle(String lineTitle) {
		this.lineTitle = lineTitle;
	}
	
	public List<LineChartPoint> getPoints() {
		return points;
	}
	public void setPoints(List<LineChartPoint> points) {
		this.points = points;
	}
		

}
