package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.kwi.commands.AppCommand;

/**
 * Class of controller for application.
 * 
 * @author Krzysztof Wisniewski
 *
 */
@Controller
@RequestMapping(value="/app")
public class AppController{
	
	
	/**
	 * Method displays default page connected with this controller.
	 * 
	 * @param command object InputCommand with page data
	 * @param request object HttpServletRequest with request from page
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView with default page connected with this controller
	 */
	@RequestMapping
	public ModelAndView displayPage(
			@ModelAttribute("command")AppCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
				
		return new ModelAndView("appJsp");
		
	}
	
	/**
	 * Method handles name sent from browser by ajax.
	 * 
	 * @param name object <code>String</code> with name
	 * @return object <code>AppCommand</code> with name
	 */
	@RequestMapping(value="/ajax", method=RequestMethod.POST)
	public @ResponseBody String ajax(@Valid @ModelAttribute(value="command")AppCommand command, BindingResult result) {
		
		if(result.hasErrors()) {
			return "message=" + result.getAllErrors().get(0).getDefaultMessage();
		}
		
		return command.getName();
		
	}

}
