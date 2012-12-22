package org.wk.services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.json";
	
	
	public void writeJSONObjectToFile(JSONObject obj, File file){
		
		FileWriter fw = null;
		
		try {
			
			fw = new FileWriter(file);
			fw.write(obj.toJSONString());
			fw.flush();
			fw.close();
			
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
	
	public JSONObject readJSONObjectFromFile(File file){
		
		JSONObject result = null;
		
		try {
			
			JSONParser parser = new JSONParser();
			result = (JSONObject)parser.parse(new FileReader(file));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	

}
