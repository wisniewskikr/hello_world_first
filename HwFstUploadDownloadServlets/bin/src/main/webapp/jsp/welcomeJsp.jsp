<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<html>


<head>
	<title>Hello World</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>
<form action="welcome.do">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2>Hello World</h2>
				<h3>Page: <b>Download</b></h3>
			</td>
		</tr>		
		<tr>
			<td>
				
				<c:choose>
					<c:when test="${requestScope.fileName == null}">
						No file to download from location: <b>${requestScope.fileLocation}</b> 
					</c:when>
					<c:otherwise>
						From location: <b>${requestScope.fileLocation}</b> 
						you can download file: <b>${requestScope.fileName}</b>. 
						To download click <a href="welcome.do?submit=Download&fileName=${requestScope.fileName}">here</a>					
					</c:otherwise>
				</c:choose>
				
			</td>
		</tr>
		<tr>
			<td><input type="submit" id="submit" name="submit" value="Back"/></td>
		</tr>
	</table>

</form>
</body>


</html>