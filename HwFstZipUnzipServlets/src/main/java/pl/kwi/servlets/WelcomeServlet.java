package pl.kwi.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.kwi.services.FileService;

@WebServlet("/welcome.do")
public class WelcomeServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	private FileService fileService;
	
	
	public WelcomeServlet(){
		fileService = new FileService();
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
		
		String zipFileName = request.getParameter("zipFileName");
		if("null".equals(zipFileName)){
			zipFileName = null;
		}
		
		request.setAttribute("zipFileName", zipFileName);
		request.setAttribute("fileLocation", fileService.getFilePath());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/welcomeJsp.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleButtonBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("hello.do?submit=Display");
		
	}
	
	private void handleLinkDownload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String zipFileName = request.getParameter("zipFileName");
		String filePath = fileService.getFilePath();
        String fileLocation = filePath + zipFileName;
				
		InputStream is = new FileInputStream(new File(fileLocation));
		OutputStream os = response.getOutputStream();
		
		ZipInputStream zis = new ZipInputStream(is);
		ZipEntry ze = zis.getNextEntry();
		String fileName = ze.getName();
    	
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		
		try {
			fileService.writeFile(zis, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
