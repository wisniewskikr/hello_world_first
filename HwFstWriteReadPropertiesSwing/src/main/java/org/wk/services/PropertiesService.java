package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.properties";
	
	
	public void writeProperitesToFile(Properties props, File file) {
		
		try {
			
			props.store(new FileOutputStream(file), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Properties readPropertiesFromFile(File file) {
		
		Properties result = new Properties();
		
		try {
			
			result.load(new FileInputStream(file));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	

}
