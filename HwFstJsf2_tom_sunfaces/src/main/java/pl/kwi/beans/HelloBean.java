package pl.kwi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kwi.entities.UserEntity;

@ManagedBean(name = "helloBean")
public class HelloBean {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(HelloBean.class);
	@ManagedProperty(value = "#{userEntity}")
	private UserEntity userEntity;
	
	
	public void displayPage(){
	}
	
	public String handleButtonOk(){
		return "/faces/welcomeJsf";		
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}	

	
}