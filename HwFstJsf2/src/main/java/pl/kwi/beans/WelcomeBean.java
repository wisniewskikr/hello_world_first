package pl.kwi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kwi.entities.UserEntity;
import pl.kwi.services.ConsoleService;

@ManagedBean(name = "welcomeBean")
public class WelcomeBean {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(WelcomeBean.class);
	@ManagedProperty(value = "#{userEntity}")
	private UserEntity userEntity;
	@ManagedProperty(value = "#{consoleService}")
	private ConsoleService consoleService;

	
	public void displayPage(){
		consoleService.displayHelloWorld(userEntity.getName());
	}
	
	public String handleButtonBack(){		
		return "/faces/helloJsf";		
	}
		
	
	public ConsoleService getConsoleService() {
		return consoleService;
	}
	public void setConsoleService(ConsoleService consoleService) {
		this.consoleService = consoleService;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}	

	
}