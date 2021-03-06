package org.wk.services;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.wk.chart.line.entities.LineChartContent;
import org.wk.chart.line.entities.LineChartLine;
import org.wk.chart.line.entities.LineChartPoint;

public class LineChartService {
	
	
	public JFreeChart createLineChart(LineChartContent content) {
		
		CategoryDataset dataset = createCategoryDataset(content);
		
		JFreeChart chart = ChartFactory.createLineChart(
	            content.getChartTitle(),    					// chart title
	            content.getxAxisTitle(),    					// domain axis label
	            content.getyAxisTitle(),    					// range axis label
	            dataset,                  						// data
	            PlotOrientation.VERTICAL, 						// orientation
	            true,                     						// include legend
	            true,                     						// tooltips
	            false                     						// urls
	     );
		
		return chart;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private CategoryDataset createCategoryDataset(LineChartContent content) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		List<LineChartLine> lines = content.getLines();
		for (LineChartLine line : lines) {
			
			String lineTitle = line.getLineTitle();
			List<LineChartPoint> points = line.getPoints();
			for (LineChartPoint point : points) {
				
				dataset.addValue(point.getyPoint(), lineTitle, point.getxPoint());
				
			}
			
		}
		
		return dataset;
		
	}
	

}
