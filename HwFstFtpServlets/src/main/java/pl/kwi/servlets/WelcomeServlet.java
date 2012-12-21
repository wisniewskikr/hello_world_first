package pl.kwi.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.kwi.services.FileService;
import pl.kwi.services.FtpService;

@WebServlet("/welcome.do")
public class WelcomeServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private FileService fileService;
	private FtpService ftpService;
	
	
	public WelcomeServlet(){
		fileService = new FileService();
		ftpService = new FtpService();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
				
		String submit = request.getParameter("submit");
		
		if("Display".equals(submit)){
			displayPage(request, response);
		}else if("Back".equals(submit)){
			handleButtonBack(request, response);
		}else if("Download".equals(submit)){
			handleLinkDownload(request, response);
		}		
		
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String fileName = request.getParameter("fileName");
		if("null".equals(fileName)){
			fileName = null;
		}
		
		request.setAttribute("fileName", fileName);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/welcomeJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleButtonBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("hello.do?submit=Display");
		
	}
	
	private void handleLinkDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String fileName = request.getParameter("fileName");
				
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		
		InputStream is = ftpService.retrieveFile(fileName, "/");
		OutputStream os = response.getOutputStream();
		try {
			fileService.writeFile(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
