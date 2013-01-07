package pl.kwi.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class DownloadJadServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		try{
			
			System.out.println("Downloading jad...");
			
			response.setContentType("text/vnd.sun.j2me.app-descriptor");
			response.addHeader("Content-Disposition", "attachment; filename=HwFstJ2Me.jad");
			
			OutputStream os= response.getOutputStream(); 
			InputStream is = DownloadJadServlet.class.getResourceAsStream("/HwFstJ2Me.jad");
			
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
