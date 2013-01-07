package pl.kwi.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download-jar.do")
public class DownloadJarServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		try{
			
			System.out.println("Downloading jar...");
			
			response.setContentType("application/java-archive");
			response.addHeader("Content-Disposition", "attachment; filename=HwFstJ2Me.jar");
			
			OutputStream os= response.getOutputStream(); 
			InputStream is = DownloadJarServlet.class.getResourceAsStream("/HwFstJ2Me.jar");
			
			while (is.available() > 0)  { 
				char c = (char) is.read(); 
			    os.write(c); 
			}    
			os.flush(); 
			is.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		
		
	}

	
}
