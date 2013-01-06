package pl.kwi.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
		
		
		JSONObject jsonObject = getJSONObjectFromRequest(request);
		String name = (String)jsonObject.get("name");
		propertyService.setProperty("name", name);
		
		System.out.println("Done. Name was added: " + name);

	}
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //


	private JSONObject getJSONObjectFromRequest(HttpServletRequest request) {

		JSONObject jsonObject = null;

		try {

			String dataJson = null;
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				dataJson = parameterNames.nextElement();
			}
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject)parser.parse(dataJson);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;

	}
	

	
}
