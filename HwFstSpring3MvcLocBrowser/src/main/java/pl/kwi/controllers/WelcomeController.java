package pl.kwi.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.HelloCommand;
import pl.kwi.commands.WelcomeCommand;

@Controller
public class WelcomeController{
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping(value="/welcome/{userName}")
	public ModelAndView displayPage(
			@ModelAttribute("command")HelloCommand command,  
			HttpServletRequest request, 
			HttpServletResponse response,
			Locale loc,
			@PathVariable String userName){
		
		String message = messageSource.getMessage("message.test", null, loc);
		System.out.println("Welcome Controller " + message);
		
		command.setUserName(userName);
		
		return new ModelAndView("welcomeJsp");
		
	}
	
	@RequestMapping(value="/handle-button-back", method=RequestMethod.POST)
	public ModelAndView handleButtonBack(
			@ModelAttribute("command")WelcomeCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("hello"));
		
	}	

}
