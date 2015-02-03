package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.ViewCommand;
import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Method displays page connected with this controller.
	 * 
	 * @param command object <code>ViewCommand</code> with page data
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @param id object <code>String</code> with id of user which should be displayed
	 * @return object <code>ModelAndView</code> with result of this method
	 */
	@RequestMapping("/{id}")
	public ModelAndView displayPage(
			@ModelAttribute("command") ViewCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		UserEntity user = userService.readUser(id);
		command.setName(user.getName());
		
		return new ModelAndView("viewJsp");
		
	}
	
	/**
	 * Method handles action after pressing button "Back".
	 * 
	 * @param command object <code>ViewCommand</code> with page data
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @return object <code>ModelAndView</code> with result of this method
	 */
	@RequestMapping(value="/back-button", method=RequestMethod.POST)
	public ModelAndView handleBackButton(
			@ModelAttribute("command")ViewCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}


}
