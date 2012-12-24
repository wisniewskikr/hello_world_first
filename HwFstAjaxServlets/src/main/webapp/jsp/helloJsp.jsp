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
	
		function sendUserNameByAjax(){
			
			var dataObj = {
				action: "sendUserName",	
				name: $('#userName').val()					
			};
			var dataJson = JSON.stringify(dataObj, null, 2);
			
			var requestUrlJson = "helloAjax.do";
	        
	        $.ajax({
	    	    type: "GET",
	    	    url: requestUrlJson,
	    	    data : dataJson,
	    	    dataType: "json",
	    	    contentType: "application/json",
	    	    success: function(data){
	    	    	$(location).attr('href',"hello.do?submit=Ok");
	    	    }
	    	})
	       		
		}
	
	</script>
</head>


<body>
<form action="hello.do">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World</h2>
				<h3>Page: <b>Hello</b></h3>
			</td>
		</tr>
		<tr>
			<td>Type your name:	<input type="text" id="userName" name="userName" size="10"/></td>
		</tr>
		<tr>
			<td><input type="button" id="submit" name="submit" value="Ok" onclick="sendUserNameByAjax();"/></td>
		</tr>		
	</table>
	
</form>
</body>


</html>