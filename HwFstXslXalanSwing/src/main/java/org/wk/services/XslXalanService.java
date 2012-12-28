package org.wk.services;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslXalanService {
	
	
	public void transformXsl(File xmlData, File xslTemplate, File result) {
		
		 try {  
			 
		      TransformerFactory tFactory = TransformerFactory.newInstance();
		      Transformer transformer = tFactory.newTransformer(new StreamSource(xslTemplate));
		      transformer.transform(new StreamSource(xmlData), new StreamResult(new FileOutputStream(result)));
		      
        } catch (Throwable t) {
          t.printStackTrace();
        }
		 
    }
		

}
