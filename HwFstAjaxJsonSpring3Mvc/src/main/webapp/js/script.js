 $(document).ready(function() {
	 
	$("#outputContent").hide();
	$("#inputContent").show();
	
	$("#inputForm").submit(function() {
		
		var dataObj = {
				name: $('#name').val()					
		};
		var dataJson = JSON.stringify(dataObj, null, 2);
		
//		var name = $('#name').val();
//		var json = { "name" : name};

				    
	   $.ajax({
	        type: "POST",
//	        data: JSON.stringify(json),
	        contentType : 'application/json',
	        url: "app/ajax",
	        data: dataJson,
//	        beforeSend: function(xhr) {
//	    		xhr.setRequestHeader("Accept", "application/json");
//	    		xhr.setRequestHeader("Content-Type", "application/json");
//	    	},
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
		
//		$.postJSON("app/ajax", json, function(data) {
//			if(response.status == "SUCCESS") {
//        		$("#inputContent").hide();
//		    	$("#outputContent").show();
//		    	$("#result").text(response.name);
//		    	$("#errors").text("");
//        	}
//        	
//        	if(response.status == "FAIL") {
//        		$("#errors").text(response.message);
//        	}
//	    });
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