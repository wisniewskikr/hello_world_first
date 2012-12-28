package org.wk.services;

import java.util.Properties;

public class PropertiesService {
	
	
	public static final String PROPERTIES_FILE_LOCATION = "/project.properties";
	
	// Description panel Hello
	public static final String PROP_DESCRIPTION_HELLO_ROW_1 = "description.hello.row.1";
	public static final String PROP_DESCRIPTION_HELLO_ROW_2 = "description.hello.row.2";
	
	// Description panel Welcome
	public static final String PROP_DESCRIPTION_WELCOME_ROW_1 = "description.welcome.row.1";
	public static final String PROP_DESCRIPTION_WELCOME_ROW_2 = "description.welcome.row.2";
	
	private Properties projectProps = new Properties();
	
	
	public PropertiesService() {
		
		try {
			
			projectProps.load(PropertiesService.class.getResourceAsStream("/project.properties"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public String getProjectProp(String key) {
		
		return projectProps.getProperty(key);
		
	}
	

}
