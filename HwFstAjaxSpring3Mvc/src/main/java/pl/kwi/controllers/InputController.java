package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.ajax.Response;
import pl.kwi.commands.InputCommand;
import pl.kwi.services.NameService;

/**
 * Class of controller for page "Input".
 * 
 * @author Krzysztof Wisniewski
 *
 */
@Controller
@RequestMapping(value="/input")
public class InputController{
	
	
	@Autowired
	private NameService nameService;
		
	
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
	
	/**
	 * Method handles action on button "Ok".
	 * 
	 * @param command object InputCommand with page data
	 * @param bindingResult object BindingResult connected with validation
	 * @param request object HttpServletRequest with request from page
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView after action on button "Ok"
	 */
	@RequestMapping(value="/handle-button-ok", method=RequestMethod.POST)
	public ModelAndView handleButtonOk(
			@Valid @ModelAttribute("command")InputCommand command,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		if(bindingResult.hasErrors()) {
			return displayPage(command, request, response);
		}
		
		String name = command.getName();
		nameService.save(name);
		
		return new ModelAndView(new RedirectView("/output", true, true, true));
		
	}
	
	@RequestMapping(value="/ajax/{name}", method=RequestMethod.GET)
	public @ResponseBody Response ajax(@PathVariable String name) {
		Response response = new Response(name);
		return response;
	}

}
