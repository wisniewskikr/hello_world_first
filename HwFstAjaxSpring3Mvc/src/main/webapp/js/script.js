 $(document).ready(function() {
			 
		 $("#outputContent").hide();
		 $("#inputContent").show();
		 
		 $("#inputForm").submit(function() {
			 
		    var name = $("#name").val();
		    if(name == null || name == "") {
		    	$("#errors").text("Please fill this field");
				  return false;
		    } else {
		    	$("#errors").text("");
		    }
		 			    
		    $.get("app/ajax/" + name, function(command) {
		    	$("#inputContent").hide();
		    	$("#outputContent").show();
		    	$("#result").text(command.name);
		    });
		    return false;
		});
		 
		 $("#back").click(function() {
			 $("#outputContent").hide();
			 $("#inputContent").show();
			 $("#name").val("");
		 });
		 
	 }
 
 );

$(function() {
	$( document ).tooltip();
});