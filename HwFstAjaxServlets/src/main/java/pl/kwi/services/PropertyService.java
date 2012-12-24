package pl.kwi.services;

import java.util.Properties;

public class PropertyService {
	
	
	private Properties props;
	
	
	public PropertyService() {
		props = System.getProperties();
	}
	
	public void setProperty(String key, String value) {
		
		props.setProperty(key, value);
		
	}
	
	public String getProperty(String key) {
		
		return (String)props.get(key);
		
	}
	

}
