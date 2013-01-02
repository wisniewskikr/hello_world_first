package org.wk.services;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerService {

	
	public final static String TEMPLATE_PATH = "src/main/templates/";
	public final static String TEMPLATE_FILE = "test.ftl";

	
	public void transformTemplate(String name, String templatePath, File output) {
		
		try {
			
			Configuration cfg = new Configuration();
			Template template = cfg.getTemplate(templatePath);
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("name", name);
			
			Writer writer = new FileWriter(output);
			
			template.process(data, writer);
			
			writer.flush();
			writer.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
