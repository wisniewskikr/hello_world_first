package pl.kwi.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome.do")
public class WelcomeServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	
	public WelcomeServlet(){
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
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/welcomeJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleButtonBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("hello.do?submit=Display");
		
	}

}
