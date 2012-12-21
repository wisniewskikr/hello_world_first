package pl.kwi.services;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

public class FileService {
	
	public String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
	
	public String getFilePath(){
		
		return System.getProperty("java.io.tmpdir") + File.separator;
		
	}
	
	public void writeFile(InputStream is, OutputStream os) throws Exception{
		
        try {            

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            
        }
		
	}
	


}
