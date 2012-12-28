package org.wk.chart.time.entities;

import org.jfree.data.time.RegularTimePeriod;

public class TimeChartPoint {
	
	
	private RegularTimePeriod xPoint;
	private double yPoint;
	
	
	public TimeChartPoint(RegularTimePeriod xPoint, double yPoint) {
		this.yPoint = yPoint;
		this.xPoint = xPoint;
	}


	public RegularTimePeriod getxPoint() {
		return xPoint;
	}
	public void setxPoint(RegularTimePeriod xPoint) {
		this.xPoint = xPoint;
	}

	public double getyPoint() {
		return yPoint;
	}
	public void setyPoint(double yPoint) {
		this.yPoint = yPoint;
	}
		

}
