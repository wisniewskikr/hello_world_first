package org.wk.jaxb.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XslData {
	
	
	private String userName;
	
	
	public XslData(String userName) {
		this.userName = userName;
	}
	public XslData() {
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
		

}
