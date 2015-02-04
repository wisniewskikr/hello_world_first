package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.TableCommand;
import pl.kwi.services.UserService;
import pl.kwi.validators.TableCommandValidator;

@Controller
@RequestMapping("/table")
public class TableController{
	
	@Autowired
	private UserService userService;
	
	@InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setValidator(new TableCommandValidator());
    }
	
	/**
	 * Method displays page connected with this controller.
	 * 
	 * @param command object <code>TableCommand</code> with page data
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @return object <code>ModelAndView</code> with result of this method
	 * @throws Exception 
	 */
	@RequestMapping("/")
	public ModelAndView displayPage(
			@ModelAttribute("command") TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		command.setUsers(userService.getUsers());
		
		return new ModelAndView("tableJsp");
		
	}	
	
	/**
	 * Method handles actions from page. Here are four CRUD actions:
	 * create, read, update and delete.
	 * 
	 * @param command object <code>TableCommand</code> with page data
	 * @param bindingResult object <code>BindingResult</code> with results of validation
	 * @param request object <code>HttpServletRequest</code> with request from browser
	 * @param response object <code>HttpServletResponse</code> with response sent to browser
	 * @param actionName object <code>String</code> with name of user`s action
	 * @return object <code>ModelAndView</code> with result of this method
	 * @throws Exception object <code>Exception</code> with exception thrown by this method
	 */
	@RequestMapping(value="/handle-table-action/{actionName}", method=RequestMethod.POST)
	protected ModelAndView handleTableAction(
			@Valid @ModelAttribute("command")TableCommand command,
			BindingResult bindingResult,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable String actionName) throws Exception{
		
		if("create".equals(actionName)) {
			return new ModelAndView(new RedirectView("/create/", true, true, true));
		} 
		
		if(bindingResult.hasErrors()) {
			return displayPage(command, request, response);
		}
		
		String id = command.getSelectedUsersIds().get(0);		
		if("view".equals(actionName)) {
			return new ModelAndView(new RedirectView("/view/" + id, true, true, true));
		} else if("edit".equals(actionName)) {
			return new ModelAndView(new RedirectView("/edit/" + id, true, true, true));
		} else if("delete".equals(actionName)) {
			return new ModelAndView(new RedirectView("/delete/" + id, true, true, true));
		} else {
			throw new Exception("There is no implementation of action with name: " + actionName);
		}
		
	}

}
