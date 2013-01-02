package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileService {
	
	public void copyFileToFile(File inputFile, File outputFile, byte[] buf) {
		
		try {
			
			if(!inputFile.exists()){
				inputFile.createNewFile();
			}
			
			if(!outputFile.exists()){
				outputFile.createNewFile();
			}
			
			InputStream is = new FileInputStream(inputFile);
			OutputStream os = new FileOutputStream(outputFile);
			
			int numRead;
		      while ( (numRead = is.read(buf) ) >= 0) {
		          os.write(buf, 0, numRead);
		      }
		      
		      is.close();
		      os.flush();
		      os.close();
		      
		      System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
