<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<html>


<head>
	<title>Hello World</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>
<form action="hello.do" method="POST" enctype="multipart/form-data">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World</h2>
				<h3>Page: <b>Upload</b></h3>
			</td>
		</tr>
		<tr>
			<td><input type="file" name="file" id="file" /></td>
		</tr>
		<tr>
			<td><input type="submit" id="submit" name="submit" value="Ok"/></td>
		</tr>		
	</table>
	
</form>
</body>


</html>