package pl.kwi.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.kwi.commands.TableCommand;

public class TableCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return TableCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		
		TableCommand command = (TableCommand) target;
		
		if(command.getSelectedUsersIds().isEmpty()) {
			errors.rejectValue("selectedUsersIds", null, "Select at least on row");
		}
		
		if(command.getSelectedUsersIds().size() > 1) {
			errors.rejectValue("selectedUsersIds", null, "Only one row can be selected");
		}
		
	}

}
