package org.wk.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.wk.jaxb.entities.UserEntity;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	
	
	public void saveName(String name){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = new UserEntity();
		user.setName(name);
		marshall(user, UserEntity.class, file);
		
	}
	
	public String loadName(){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = (UserEntity) unmarshall(UserEntity.class, file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private void marshall(Object obj, Class<? extends Object> clazz, File file){
		
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
	
	private Object unmarshall(Class<? extends Object> clazz, File file){
		
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
