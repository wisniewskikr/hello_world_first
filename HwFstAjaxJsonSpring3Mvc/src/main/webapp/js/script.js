 $(document).ready(function() {
	 
	$("#outputContent").hide();
	$("#inputContent").show();
	
	$("#inputForm").submit(function() {
				    
	   $.ajax({
	        type: "POST",
	        url: "app/ajax",
	        data: $("#inputForm").serialize(),
	        success: function(response){
	        	
	        	if(response.status == "SUCCESS") {
	        		$("#inputContent").hide();
			    	$("#outputContent").show();
			    	$("#result").text(response.name);
			    	$("#errors").text("");
	        	}
	        	
	        	if(response.status == "FAIL") {
	        		$("#errors").text(response.message);
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