package org.wk.chart.line.entities;

public class LineChartPoint {
	
	
	private double yPoint;
	private String xPoint;
	
	
	public LineChartPoint(String xPoint, double yPoint) {
		this.yPoint = yPoint;
		this.xPoint = xPoint;
	}
	
	
	public double getyPoint() {
		return yPoint;
	}
	public void setyPoint(double yPoint) {
		this.yPoint = yPoint;
	}
	
	public String getxPoint() {
		return xPoint;
	}
	public void setxPoint(String xPoint) {
		this.xPoint = xPoint;
	}
		

}
