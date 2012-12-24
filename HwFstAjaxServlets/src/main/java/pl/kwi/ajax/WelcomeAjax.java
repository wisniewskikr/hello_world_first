package pl.kwi.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pl.kwi.services.PropertyService;

@WebServlet("/welcomeAjax.do")
public class WelcomeAjax extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	private PropertyService propertyService;
	
	
	public WelcomeAjax() {
		propertyService = new PropertyService();
	}

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
				
		JSONObject jsonObject = getJSONObjectFromRequest(request);
		String action = (String)jsonObject.get("action");
	
		if("loadUserName".equals(action)){
			loadUserName(request, response);
		}
		
	}
	
	private void loadUserName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String name = propertyService.getProperty("name");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toJSONString());
		out.flush();
		
		
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
