/*
 * Function sends current form with specified
 * action.
 */
 function send(action){
	document.form.action = action;
	document.form.submit();	
}