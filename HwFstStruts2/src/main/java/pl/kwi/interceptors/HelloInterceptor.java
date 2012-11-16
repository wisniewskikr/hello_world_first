package pl.kwi.interceptors;

import pl.kwi.actions.HelloAction;
import pl.kwi.services.ConsoleService;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HelloInterceptor extends AbstractInterceptor{
	
	private static final long serialVersionUID = 1L;
	private ConsoleService consoleService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
				
		String result = invocation.invoke();
		
		HelloAction helloAction = (HelloAction)invocation.getAction();
		consoleService.displayHelloWorld(helloAction.getUserName());
		
		return result;
		
	}

	@Override
	public void init() {
		consoleService = new ConsoleService();
	}
	
	

}
