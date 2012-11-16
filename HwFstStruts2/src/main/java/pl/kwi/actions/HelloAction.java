package pl.kwi.actions;

public class HelloAction {
	
	
	private String userName;
	
	
	public String handleOkButton(){
		return "SUCCESS";
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	

}
