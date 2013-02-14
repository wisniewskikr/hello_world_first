package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.wk.entities.UserEntity;

import com.thoughtworks.xstream.XStream;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	
	
	public void saveName(String name){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = new UserEntity(name);
		toXml(user, file);		
		
	}
	
	public String loadName(){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = (UserEntity)fromXml(file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
    private void toXml(Object o, File xmlFile) {
    	    	
    	try {
    		XStream xStream = new XStream();
        	xStream.toXML(o, new FileOutputStream(xmlFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    private Object fromXml(File xmlFile) {
		
    	Object o = null;
    	
		try {
			XStream xStream = new XStream();
			o = xStream.fromXML(new FileInputStream(xmlFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
		
    }
	

}
