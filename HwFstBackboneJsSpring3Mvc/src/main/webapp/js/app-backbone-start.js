// Necessary in JSP. Add this to avoid 
// JSP marks in backbone template and compilation errors
_.templateSettings = {
	    interpolate: /\{\{(.+?)\}\}/gim,
    evaluate: /\{\{(.+?)\}\}/gim,
    escape: /\{\{\-(.+?)\}\}/gim
};

var inputView = new InputView();