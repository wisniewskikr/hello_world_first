package org.wk.chart.time.entities;

import java.util.ArrayList;
import java.util.List;

public class TimeChartLine {
	
	
	private String lineTitle;
	private List<TimeChartPoint> points = new ArrayList<TimeChartPoint>();
		
	
	public TimeChartLine(String lineTitle, List<TimeChartPoint> points) {
		this.lineTitle = lineTitle;
		this.points = points;
	}
	
	
	public String getLineTitle() {
		return lineTitle;
	}
	public void setLineTitle(String lineTitle) {
		this.lineTitle = lineTitle;
	}
	
	public List<TimeChartPoint> getPoints() {
		return points;
	}
	public void setPoints(List<TimeChartPoint> points) {
		this.points = points;
	}
		

}
