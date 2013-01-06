package pl.kwi.screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

import pl.kwi.midlet.HelloWorldMidlet;

public class WelcomeScreen implements CommandListener{
	
	
	private HelloWorldMidlet midlet;
	

	public WelcomeScreen(String name) {
		
		midlet = HelloWorldMidlet.getMidlet();		
		Form form = new Form("Hello World");
		
		StringItem message = new StringItem(null, "Your name is: " + name);
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

}
