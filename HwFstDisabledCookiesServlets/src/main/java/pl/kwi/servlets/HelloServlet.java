package pl.kwi.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello.do")
public class HelloServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
				
		String submit = request.getParameter("submit");
		
		if("Display".equals(submit)){
			displayPage(request, response);
		}else if("Ok".equals(submit)){
			handleButtonOk(request, response);
		}		
		
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/helloJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleButtonOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String userName = request.getParameter("userName");
					
		response.sendRedirect(response.encodeRedirectURL("welcome.do?submit=Display&&userName=" + userName));
		
	}

}
