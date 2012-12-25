package org.wk.services;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.wk.chart.time.entities.TimeChartContent;
import org.wk.chart.time.entities.TimeChartLine;
import org.wk.chart.time.entities.TimeChartPoint;

public class TimeChartService {
	
	
	public JFreeChart createTimeChart(TimeChartContent content) {
		
		XYDataset dataset = createXYDataset(content);
		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            content.getChartTitle(),    					// chart title
	            content.getxAxisTitle(),    					// domain axis label
	            content.getyAxisTitle(),    					// range axis label
	            dataset,                  						// data
	            true,                     						// include legend
	            true,                     						// tooltips
	            false                     						// urls
	     );
		
		return chart;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private XYDataset createXYDataset(TimeChartContent content) {
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		List<TimeChartLine> lines = content.getLines();
		for (TimeChartLine line : lines) {
			
			String lineTitle = line.getLineTitle();
			List<TimeChartPoint> points = line.getPoints();
			TimeSeries series = new TimeSeries(lineTitle);
			for (TimeChartPoint point : points) {
				
				series.add(point.getxPoint(), point.getyPoint());
			}
			
			dataset.addSeries(series);
			
		}
		
		return dataset;
		
	}
	

}
