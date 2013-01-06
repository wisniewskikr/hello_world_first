package pl.kwi.screens;

import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import org.json.me.JSONObject;

import pl.kwi.midlet.HelloWorldMidlet;

public class WelcomeScreen implements CommandListener{
	
	
	private static final String BASIC_URL = "http://192.168.1.151:8080/HwFstJ2MeHttpJsonServlets";
	
	private HelloWorldMidlet midlet;
	

	public WelcomeScreen() {
		
		midlet = HelloWorldMidlet.getMidlet();	
		runConnectionThread();
		
	}
	
	public WelcomeScreen(String name) {
		
		midlet = HelloWorldMidlet.getMidlet();		
		Form form = new Form("Hello World");
		
		StringItem message = new StringItem(null, "Hello World: " + name);
		Command back = new Command("Back", Command.OK, 0);
		Command exit = new Command("Exit", Command.EXIT, 0);
		
		form.append(message);
		form.addCommand(back);
		form.addCommand(exit);
		
		form.setCommandListener(this);
		
		Display display = Display.getDisplay(midlet);
		display.setCurrent(form);
		
	}
	
	public void commandAction(Command command, Displayable dispalyable) {
		
		if(command.getCommandType() == Command.OK){
			new HelloScreen();
		}
		
		if(command.getCommandType() == Command.EXIT){
			midlet.notifyDestroyed();
		}
		
	}
	
	protected void runConnectionThread() {
		
		new WaitScreen();
		
		Thread t =  new Thread() {
	        public void run() {
	          getNameFromServlet();
	        }
	      };
	      t.start();
		
	}
	
	public void getNameFromServlet() {
		
		String name = null;
		
		try{
			
			HttpConnection conn = (HttpConnection)Connector.open(BASIC_URL + "/welcome.do");
			conn.setRequestMethod(HttpConnection.POST);
			conn.setRequestProperty("Accept", "application/json");
			
			int responseCode = conn.getResponseCode();
			if(responseCode != 200){
				throw new Exception("Response code from servlet is: " + responseCode);
			}
			
			StringBuffer sb = new StringBuffer();
		     InputStream is = conn.openInputStream();
		      int chr;
		      while ((chr = is.read()) != -1){
		    	  sb.append((char) chr);		    	  
		      }
		      
		      JSONObject obj = new JSONObject(sb.toString());
		      name = obj.getString("name");
		      
		      conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		new WelcomeScreen(name);
		
	}

}
