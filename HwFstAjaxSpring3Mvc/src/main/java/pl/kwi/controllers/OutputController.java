package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.InputCommand;
import pl.kwi.commands.OutputCommand;
import pl.kwi.services.NameService;

/**
 * Class of controller for page "Output".
 * 
 * @author Krzysztof Wisniewski
 *
 */
@Controller
@RequestMapping(value="/output")
public class OutputController{
	
	
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
		
		String name = nameService.load();
		command.setName(name);
		
		return new ModelAndView("outputJsp");
		
	}
	
	/**
	 * Method handles action on button "Back".
	 * 
	 * @param command object OutputCommand with page data
	 * @param request object HttpServletRequest with request from page
	 * @param response object HttpServletResponse with response to page
	 * @return object ModelAndView after action on button "Back"
	 */
	@RequestMapping(value="/handle-button-back", method=RequestMethod.POST)
	public ModelAndView handleButtonBack(
			@ModelAttribute("command")OutputCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/input", true, true, true));
		
	}	

}
