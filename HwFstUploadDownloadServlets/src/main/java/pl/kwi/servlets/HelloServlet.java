package pl.kwi.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pl.kwi.services.FileService;

@WebServlet("/hello.do")
@MultipartConfig
public class HelloServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private FileService fileService;
	
	
	public HelloServlet(){
		fileService = new FileService();
	}
	
	
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
		
		final Part filePart = request.getPart("file");
        final String fileName = fileService.getFileName(filePart);
        String filePath = fileService.getFilePath();
        String fileLocation = filePath + fileName;
        
        if(filePart.getSize() == 0){
        	response.sendRedirect("welcome.do?submit=Display&fileName=" + null);
        	return;
        }
        
        InputStream is = filePart.getInputStream();
        OutputStream os = new FileOutputStream(new File(fileLocation));
        
        try {
			fileService.writeFile(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("welcome.do?submit=Display&fileName=" + fileName);
		
	}
	


}
