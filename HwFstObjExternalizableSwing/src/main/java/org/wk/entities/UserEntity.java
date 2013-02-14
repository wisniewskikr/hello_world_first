package org.wk.entities;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserEntity implements Externalizable{
	
	
	private static final long serialVersionUID = 1L;
	private String name;

	
	public UserEntity() {
	}
	
	public UserEntity(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		name = (String)in.readObject();
		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(name);		
	}	
	

}
