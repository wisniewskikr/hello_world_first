package pl.kwi.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.kwi.services.PropertyService;

@WebServlet("/hello.do")
public class HelloServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private PropertyService propertyService;


	public HelloServlet() {
		propertyService = new PropertyService();
	}


	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String name = request.getParameter("name");
		propertyService.setProperty("name", name);
		
		System.out.println("Done. Name was added: " + name);

	}

	
}
