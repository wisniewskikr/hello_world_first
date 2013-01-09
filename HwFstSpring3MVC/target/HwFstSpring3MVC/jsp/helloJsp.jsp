<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>


<head>
	<title>Hello World Spring 3 MVC</title>
	<base href="${pageContext.request.contextPath}/">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>
<form name="form" method="post" action="handle-button-ok">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World Spring 3 MVC</h2>
				<h3>Page: <b>Hello</b></h3>
			</td>
		</tr>
		<tr>
			<td>Type your name:	<input type="text" id="userName" name="userName" value="${command.userName}" size="10"/></td>
		</tr>
		<tr>
			<td><input type="submit" id="ok" name="ok" value="OK"/></td>
		</tr>		
	</table>
	
</form>
</body>


</html>