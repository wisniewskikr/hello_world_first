package org.wk.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TextService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir");
	public final static String FILE_NAME = "test.txt";
	
	
	public void writeTextToFile(String text, File file){
		
		OutputStream os = null;
		
		try {
			
			os = new FileOutputStream(file);
			os.write(text.getBytes());
			os.flush();
			
			System.out.println("Done.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			try {				
				if(os != null){
					os.close();
				}				
			} catch (Exception e2) {
				e2.printStackTrace();
			}			
			
		}
		
	}
	
	public String readTextFromFile(File file){
		
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		
		try {
			
			is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				sb.append(strLine);
			}
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {				
				if(is != null){
					is.close();
				}				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		return sb.toString();
		
	}

}
