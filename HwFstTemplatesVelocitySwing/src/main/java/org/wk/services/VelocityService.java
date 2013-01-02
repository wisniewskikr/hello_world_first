package org.wk.services;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityService {

	
	public final static String TEMPLATE_PATH = "src/main/templates/";
	public final static String TEMPLATE_FILE = "test.vm";
	
	
	public void transformTemplate(String name, String templatePath, File output) {
		
		try {
			
	        VelocityEngine ve = new VelocityEngine();
	        ve.init();
	        
	        Template t = ve.getTemplate(templatePath);
	        VelocityContext context = new VelocityContext();
	        context.put("name", name);
	        Writer writer = new FileWriter(output);
	        t.merge( context, writer );
	        
	        writer.flush();
	        writer.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
