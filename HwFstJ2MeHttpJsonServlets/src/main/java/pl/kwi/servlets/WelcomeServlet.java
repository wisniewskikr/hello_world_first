package pl.kwi.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import pl.kwi.services.PropertyService;

@WebServlet("/welcome.do")
public class WelcomeServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	private PropertyService propertyService;


	public WelcomeServlet() {
		propertyService = new PropertyService();
	}


	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String name = propertyService.getProperty("name");
		
		System.out.println("Done. Name was read: " + name);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toJSONString());
		out.flush();

	}


}
