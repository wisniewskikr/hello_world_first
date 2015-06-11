package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.kwi.commands.InputCommand;

/**
 * Class of controller for page "Input".
 * 
 * @author Krzysztof Wisniewski
 *
 */
@Controller
@RequestMapping(value="/input")
public class InputController{
	
	
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
			@ModelAttribute("command")InputCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
				
		return new ModelAndView("inputJsp");
		
	}
	
	@RequestMapping(value="/ajax/{name}", method=RequestMethod.GET)
	public @ResponseBody InputCommand ajax(@PathVariable String name) {
		InputCommand command = new InputCommand();
		command.setName(name);
		return command;
	}

}
