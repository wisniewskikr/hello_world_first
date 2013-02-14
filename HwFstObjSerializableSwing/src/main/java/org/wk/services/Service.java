package org.wk.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.wk.entities.UserEntity;

public class Service {
	
	
	public final static String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
	public final static String FILE_NAME = "test.ser";
	
	
	public void saveName(String name){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = new UserEntity(name);
		serialize(user, file);		
		
	}
	
	public String loadName(){
		
		File file = new File(FILE_PATH + FILE_NAME);
		UserEntity user = (UserEntity)deserialize(file);
		return user.getName();
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	private void serialize(Object obj, File file){
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
			os.writeObject(obj);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private Object deserialize(File file) {
		
		Object obj = null;
		
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
			obj = is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
		
	}

}
