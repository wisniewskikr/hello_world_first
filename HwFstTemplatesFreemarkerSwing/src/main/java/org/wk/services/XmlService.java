package org.wk.services;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class XmlService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String XML_FILE = "test.xml";


	public Object readObjectFromFile(Class<? extends Object> clazz, File file){
		
		Object result = null;
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			result = jaxbUnmarshaller.unmarshal(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}		

}
