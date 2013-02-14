package org.wk.services;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.wk.entities.UserEntity;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.xml";
	
	
	public void saveName(String name){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = new UserEntity(name);
		encodeObject(user, file);		
		
	}
	
	public String loadName(){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = (UserEntity)decodeObject(file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
    private void encodeObject(Object o, File xmlFile) {
    	
    	try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(xmlFile));
			encoder.writeObject(o);
			encoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    private Object decodeObject(File xmlFile) {
		
    	Object o = null;
    	
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(xmlFile));
			o = decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return o;
		
    }
	

}
