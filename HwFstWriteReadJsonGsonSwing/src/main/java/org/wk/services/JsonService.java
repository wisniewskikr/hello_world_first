package org.wk.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;

public class JsonService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.json";
	
	
	public void writeObjectToJsonFile(Object obj, File file){
		
		FileWriter fw = null;
		
		try {
			
			Gson gson = new Gson();
			String json = gson.toJson(obj);
			
			fw = new FileWriter(file);
			fw.write(json);
			fw.flush();
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null){fw.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public Object readObjectFromJsonFile(File file, Class<? extends Object> clazz) {
		
		Object result = null;
		BufferedReader br = null;
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			
			Gson gson = new Gson();
			result = gson.fromJson(br, clazz);			
			
			System.out.println("Done");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null){br.close();}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	

}
