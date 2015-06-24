package pl.kwi.ajax;

import java.io.Serializable;

public class AppResponse implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String status;
	private String name;
	private String message;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
