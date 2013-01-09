package pl.kwi.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.HelloCommand;

@Controller
public class HelloController{
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping(value="/hello")
	public ModelAndView displayPage(
			@ModelAttribute("command")HelloCommand command, 
			HttpServletRequest request, 
			HttpServletResponse response,
			Locale loc){
		
		String message = messageSource.getMessage("message.test", null, loc);
		System.out.println("Hello Controller " + message);
				
		return new ModelAndView("helloJsp");
		
	}
	
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public ModelAndView handleButtonOk(
			@ModelAttribute("command")HelloCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		String userName = command.getUserName();
				
		return new ModelAndView(new RedirectView("welcome/" + userName));
		
	}

}
