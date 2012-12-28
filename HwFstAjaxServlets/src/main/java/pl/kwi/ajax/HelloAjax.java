package pl.kwi.ajax;

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

@WebServlet("/helloAjax.do")
public class HelloAjax extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	private PropertyService propertyService;
	
	
	public HelloAjax() {
		propertyService = new PropertyService();
	}

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
				
		JSONObject jsonObject = getJSONObjectFromRequest(request);
		String action = (String)jsonObject.get("action");
	
		if("sendUserName".equals(action)){
			sendUserName(request, response, jsonObject);
		}
		
	}
	
	private void sendUserName(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObject) 
			throws ServletException, IOException{
		
		String name = (String)jsonObject.get("name");
		propertyService.setProperty("name", name);
		
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
