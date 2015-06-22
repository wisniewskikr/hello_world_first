 $(document).ready(function() {
	 
	$("#outputContent").hide();
	$("#inputContent").show();
	
	$("#inputForm").submit(function() {
		 
	   $.ajax({
	        type: "GET",
	        url: "app/ajax",
	        data: $("#inputForm").serialize(),
	        success: function(response){
	        	
	        	if(response.startsWith("message=")) {
	        		var errorArray = response.split("=");
	        		$("#errors").text(errorArray[1]);
	        	} else {
	        		$("#inputContent").hide();
			    	$("#outputContent").show();
			    	$("#result").text(response);
			    	$("#errors").text("");
	        	}
		    	
	        }
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