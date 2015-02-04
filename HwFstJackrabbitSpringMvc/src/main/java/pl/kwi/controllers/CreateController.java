package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.CreateCommand;
import pl.kwi.commands.EditCommand;
import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Controller
@RequestMapping("/create")
public class CreateController {
	
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Method displays page connected with this controller.
	 * 
	 * @param command object <code>CreateCommand</code> with page data
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @return object <code>ModelAndView</code> with result of this method
	 */
	@RequestMapping("/")
	public ModelAndView displayPage(
			@ModelAttribute("command") CreateCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView("createJsp");
		
	}
	
	/**
	 * * Method handles action after pressing button "Create".
	 * 
	 * @param command object <code>CreateCommand</code> with page data
	 * @param bindingResult object <code>BindingResult</code> with results of validation
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @return object <code>ModelAndView</code> with result of this method
	 * @throws Exception 
	 */
	@RequestMapping(value="/create-button", method=RequestMethod.POST)
	public ModelAndView handleCreateButton(
			@Valid @ModelAttribute("command") CreateCommand command,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		if(bindingResult.hasErrors()) {
			return displayPage(command, request, response);
		}
		
		UserEntity user = new UserEntity();
		user.setId(command.getId());
		user.setName(command.getName());
		userService.createUser(user);
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}
	
	/**
	 * Method handles action after pressing button "Back".
	 * 
	 * @param command object <code>CreateCommand</code> with page data
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @return object <code>ModelAndView</code> with result of this method
	 */
	@RequestMapping(value="/back-button", method=RequestMethod.POST)
	public ModelAndView handleBackButton(
			@ModelAttribute("command")EditCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}


}
