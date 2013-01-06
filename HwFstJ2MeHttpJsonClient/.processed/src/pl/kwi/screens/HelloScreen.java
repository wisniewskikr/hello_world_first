package pl.kwi.screens;

import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import org.json.me.JSONObject;

import pl.kwi.midlet.HelloWorldMidlet;

public class HelloScreen implements CommandListener{
	
	
	private static final String BASIC_URL = "http://192.168.1.151:8080/HwFstJ2MeHttpJsonServlets";
	
	private HelloWorldMidlet midlet;
	private TextField text;
	

	public HelloScreen() {
		
		midlet = HelloWorldMidlet.getMidlet();		
		Form form = new Form("Hello World");
		
		text = new TextField("Type your name: ", "", 75, TextField.ANY);
		Command ok = new Command("Ok", Command.OK, 0);
		Command exit = new Command("Exit", Command.EXIT, 0);
		
		form.append(text);
		form.addCommand(ok);
		form.addCommand(exit);
		
		form.setCommandListener(this);
		
		Display display = Display.getDisplay(midlet);
		display.setCurrent(form);
		
	}

	public void commandAction(Command command, Displayable displayable) {
		if(command.getCommandType() == Command.OK){
			runConnectionThread();  
		}
		
		if(command.getCommandType() == Command.EXIT){
			midlet.notifyDestroyed();
		}
		
	}
	
	protected void runConnectionThread() {
		
		new WaitScreen();
		
		Thread t =  new Thread() {
			public void run() {
	          sendNameToServlet();
	        }
	      };
	      t.start();
		
	}
	
	public void sendNameToServlet() {
		
		try{
			
			JSONObject obj = new JSONObject();
			obj.put("name", text.getString());
			String json = obj.toString();
					
			HttpConnection conn = (HttpConnection)Connector.open(BASIC_URL + "/hello.do");
			conn.setRequestMethod(HttpConnection.POST);
			// Do not accept application/json
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStream os = conn.openOutputStream();
			os.write(json.getBytes());
			os.flush();
			
			int responseCode = conn.getResponseCode();
			if(responseCode != 200){
				throw new Exception("Response code from servlet is: " + responseCode);
			}
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		new WelcomeScreen();
		
	}

}
