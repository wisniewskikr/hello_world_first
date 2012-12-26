package org.wk.services;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.js";
	
	
	public void writeStreamInputToOuptpu(InputStream is, OutputStream os, byte[] buf ) {
		
		try {
			
			int numRead;
		      while ( (numRead = is.read(buf) ) >= 0) {
		          os.write(buf, 0, numRead);
		      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		      
	} 

}
