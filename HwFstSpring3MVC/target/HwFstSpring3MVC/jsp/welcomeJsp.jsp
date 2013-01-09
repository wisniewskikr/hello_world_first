<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>	

<html>


<head>
	<title>Hello World Spring 3 MVC</title>
	<base href="${pageContext.request.contextPath}/">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>
<form method="post" action="handle-button-back">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World Spring 3 MVC</h2>
				<h3>Page: <b>Welcome</b></h3>
			</td>
		</tr>		
		<tr>
			<td>
				Hello World: <b>${command.userName} </b>
			</td>
		</tr>
		<tr>
			<td><input type="submit" id="back" name="back" value="Back"/></td>
		</tr>
	</table>

</form>
</body>


</html>