<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
	<title>Hello World</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World</h2>
				<h3>Page: <b>Hello World</b></h3>
			</td>
		</tr>
		<tr>
			<td>
				<applet 
					code = 'org.wk.applets.HelloWorldApplet.class' 
				    archive = 'applets/HwFstAppletInsideSwing.jar'
				    width = 400
				    height = 255 />
			</td>
		</tr>
	</table>

</body>
</html>