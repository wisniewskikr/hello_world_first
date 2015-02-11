<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>	

<html>


<head>
	<title>Hello World - Input</title>
	<base href="${pageContext.request.contextPath}/">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.3/smoothness/jquery-ui-1.10.3.custom.css">
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="js/script.js"></script>	
	<script type="text/javascript">
	
		function sendNameByAjax(){
						
			var dataObj = {
				action: "name",	
				name: $('#name').val()					
			};
			var dataJson = JSON.stringify(dataObj, null, 2);
			
			var requestUrlJson = "input/ajax";
	        
	        $.ajax({
	    	    type: "GET",
	    	    url: requestUrlJson,
	    	   	//data : dataJson,
	    	    dataType: "json",
	    	    contentType: "application/json",
	    	    success: function(data){
	    	    	alert("success");
	    	    	alert(data.message);
	    	    }
	    	})
	       		
		}
	
	</script>
</head>


<body>
<spring:form method="post" action="input/handle-button-ok" commandName="command">

	<div class="page">
		<div id="title" name="title" class="title"><h2>Hello World</h2></div>
		<div id="subtitle" name="subtitle" class="subtitle"><h3>Page: <b>Input</b></h3></div>
		<div id="content" name="content" class="content">
			<div class="contentElement">
				<div class="text">Name * <spring:errors path="name" cssClass="error" /> </div>
				<div class="input"><input type="text" id="name" name="name" value="${name}" /></div>
				<div class="description">Type your name here</div>
			</div>
			
		</div>
		<div id="buttons" name="buttons" class="buttons">
			<input type="submit" id="ok" name="ok" value="OK" title="Go to next page"/>
			<input type="button" id="tmp" name="tmp" value="Tmp" title="Tmp" onclick="sendNameByAjax();"/>
		</div>
	</div>		

</spring:form>
</body>


</html>