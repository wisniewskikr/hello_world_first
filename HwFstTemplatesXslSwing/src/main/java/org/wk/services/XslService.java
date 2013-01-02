package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslService {
	
	
	public final static String TEMPLATE_PATH = "src/main/templates/";
	public final static String TEMPLATE_FILE = "test.xslt";
	


	public void transformTemplate(File inputXml, String templatePath, File outputFile) {
		
		 try {  
			 
		      TransformerFactory tFactory = TransformerFactory.newInstance();
		      Transformer transformer = tFactory.newTransformer(new StreamSource(templatePath));
		      transformer.transform(new StreamSource(inputXml), new StreamResult(new FileOutputStream(outputFile)));
		      
       } catch (Throwable t) {
         t.printStackTrace();
       }
		 
   }

	
}
