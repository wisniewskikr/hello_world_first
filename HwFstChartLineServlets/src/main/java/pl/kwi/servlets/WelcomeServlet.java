package pl.kwi.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.wk.chart.line.entities.LineChartContent;
import org.wk.chart.line.entities.LineChartLine;
import org.wk.chart.line.entities.LineChartPoint;

import pl.kwi.services.ConsoleService;
import pl.kwi.services.LineChartService;

@WebServlet("/welcome.do")
public class WelcomeServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private ConsoleService consoleService;
	private LineChartService lineChartService;
	
	
	public WelcomeServlet(){
		consoleService = new ConsoleService();
		lineChartService = new LineChartService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
				
		String submit = request.getParameter("submit");
		
		if("Display".equals(submit)){
			displayPage(request, response);
		}else if("Back".equals(submit)){
			handleButtonBack(request, response);
		}		
		
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String userName = request.getParameter("userName");
		
		consoleService.displayHelloWorld(userName);
		
		JFreeChart freeChart = null;
		
		try {
			
			List<LineChartPoint> points = new ArrayList<LineChartPoint>();
			char[] charArray = userName.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				String xPoint = String.valueOf(charArray[i]);
				double yPoint = Double.valueOf(xPoint);
				points.add(new LineChartPoint(xPoint, yPoint));
			}
			
			List<LineChartLine> lines = new ArrayList<LineChartLine>();
			LineChartLine line = new LineChartLine("Test line", points);
			lines.add(line);
			
			LineChartContent content = new LineChartContent("Test chart", "X Axis", "Y Axis", lines);
			
			freeChart = lineChartService.createLineChart(content);
			
		} catch (Exception e) {}
		
		String contextPath = request.getServletContext().getRealPath("/");
		String fileName = "Test.png";
		String fileLocation = contextPath + "img" + File.separator + fileName;
		
		if(freeChart != null) {
			
			File file = new File(fileLocation);
			lineChartService.writeFreeChartToFile(freeChart, file);
			
		}	
		
		request.setAttribute("fileLocation", "img" + File.separator + fileName);
		request.setAttribute("userName", userName);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/welcomeJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleButtonBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("hello.do?submit=Display");
		
	}

}
