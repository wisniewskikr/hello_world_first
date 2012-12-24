<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html>


<head>
	<title>Hello World Servlets</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="styles/style.css">
	<script type="text/javascript" src="scripts/script.js"></script>
	<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
	
		function loadUserNameByAjax(){
			
			var dataObj = {
					action: "loadUserName"					
				};
				var dataJson = JSON.stringify(dataObj, null, 2);
	
			var requestUrlJson = "welcomeAjax.do";
	
	        $.ajax({
	    	    type: "GET",
	    	    url: requestUrlJson,
	    	    data : dataJson,
	    	    dataType: "json",
	    	    contentType: "application/json",
	    	    success: function(data){
	    	    	$("#userName").html(data.name);	    	    	
	    	    }
	    	})
	
		}
	
	</script>
</head>


<body onload="loadUserNameByAjax();">
<form action="welcome.do">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World</h2>
				<h3>Page: <b>Welcome</b></h3>
			</td>
		</tr>		
		<tr>
			<td>
				Hello World: <b><span id="userName"/></b>
			</td>
		</tr>
		<tr>
			<td><input type="submit" id="submit" name="submit" value="Back"/></td>
		</tr>
	</table>

</form>
</body>


</html>