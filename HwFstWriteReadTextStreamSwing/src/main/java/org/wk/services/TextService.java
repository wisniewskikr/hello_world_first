package org.wk.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.txt";
	
	
	public void writeTextToFile(String text, File file){
		
		try {
			
			FileWriter fw = new FileWriter(file);
			fw.write(text);
			fw.flush();
			fw.close();
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String readTextFromFile(File file){

		StringBuilder sb = new StringBuilder();
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				sb.append(strLine);
			}
			br.close();
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return sb.toString();
		
	}

}
