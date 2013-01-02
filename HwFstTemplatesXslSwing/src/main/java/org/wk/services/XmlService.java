package org.wk.services;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlService {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String XML_INPUT_FILE = "input.xml";
	public final static String XML_OUTPUT_FILE = "test.xml";




	public void writeObjectToFile(Object obj, Class<? extends Object> clazz, File file){
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(obj, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
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
