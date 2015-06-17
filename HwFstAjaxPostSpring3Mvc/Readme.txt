DESCRIPTION
===========

This is example project with usage Ajax and method GET with framework Spring 3 MVC. 


Recommended deployment of project:
----------------------------------
Maven command:
mvn clean install -Ploc,deploy 

Recommended usage of project:
-----------------------------
Browser url:
http://localhost:8080/HwFstAjaxGetSpring3Mvc





PRECONDITIONS
=============

1. TOOLS
--------
This example project requires:
- Java (tested for version 1.7.0_10);
- Maven (tested for version 3.0.4);
- Tomcat (tested for version 7.0.34).

2. CONFIGURATION
----------------
All flexible configuration of project (server`s urls, logins, passwords etc.) can be changed in file:
<project_home>/project.properties





DEPLOYMENT
==========

You can deploy this application in two ways:
1. Copy war file
2. Use Maven plugin for deployment


Ad 1\ Copy war file
===================
You can do it using following steps:
- Open console;
- Go to project folder "HwFstAjaxGetSpring3Mvc;
- Use Maven command for building project. Command: 
  
  mvn clean install
  
- Copy file "Spring3Mvc-example-helloworld.war" from <project_home>/target to <tomcat_home>/webapp


Ad 2\ Use Maven plugin for deployment
=====================================  
You can do it using following steps:
- Go to <tomcat_home>/conf/tomcat-users.xml and add user in manager role. For instance:

  <user username='admin' password='admin' roles='manager-gui,admin-gui,manager-script'/>
  
- Set server informations in file: <project_home>/project.properties. You have to set:
  loc.server.url, loc.server.username, loc.server.password;  
- Open console;
- Go to project folder "Spring3Mvc-example-helloworld";
- Use Maven command for building and deployment project. Command:
 
  mvn clean install -Ploc,deploy  





USAGE
=====

Type in browser:

http://localhost:8080/HwFstAjaxGetSpring3Mvc


-----------------------------------


 $(document).ready(function() {
			 
			 $("#inputForm").submit(function() {
				 	alert("inputForm");
				    var form = $(this).serialize();
				    $.postJSON("input/ajax2", form, function(data) {
				    	alert(data.message);
				    });
				    return false;
			});
			
			 
		 });
		 
		 
		 
		 	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public @ResponseBody Response ajax() {
		Response response = new Response("Ok");
		return response;
	}
	
	@RequestMapping(value="/ajax2", method=RequestMethod.GET)
	public @ResponseBody Response ajax2(@RequestBody InputCommand command) {
		System.out.println("name: " + command.getName());
		Response response = new Response("Ok");
		return response;
	}
	
	<script type="text/javascript">
	
		 $(document).ready(function() {
			 
			$("#outputContent").hide();
			$("#inputContent").show();
			alert("0");
			
			$("#ok").click(function() {
				 
			   var name = $("#name").val();
						    
			   $.ajax({
			        type: "POST",
			        url: "app/ajax",
			        data: "name=" + name,
			        success: function(response){
				    	$("#inputContent").hide();
				    	$("#outputContent").show();
				    	$("#result").text(response.name);
				    	alert("1");
			        },
			        error: function(e){
			        	alert('Error: ' + e);
			        	alert("2");
			        }
			   });
			   
			});
			
			$("#back").click(function() {
				 $("#outputContent").hide();
				 $("#inputContent").show();
				 $("#name").val("");
				 alert("3");
			});
			
			}
		
		);
	
	</script>
	
	
	<script type="text/javascript">
	
		 $(document).ready(function() {
			 
			$("#outputContent").hide();
			$("#inputContent").show();
			alert("0");
			
			$("#ok").click(function() {
				 
			   var name = $("#name").val();
						    
			   $.post("app/ajax", {name: name}).done(function( data ) {
				   $("#inputContent").hide();
			    	$("#outputContent").show();
			    	$("#result").text(data.name);
			    	alert("1");
			   });
			   
			});
			
			$("#back").click(function() {
				 $("#outputContent").hide();
				 $("#inputContent").show();
				 $("#name").val("");
				 alert("3");
			});
			
			}
		
		);
	
	</script>
	