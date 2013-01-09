<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>


<head>
	<title><spring:message code="head.title"/></title>
	<base href="${pageContext.request.contextPath}/">
	<script type="text/javascript" src="scripts/script.js"></script>
	<link type="text/css" rel="stylesheet" href="styles/style.css">
</head>


<body>
<form name="form" method="post" action="handle-button-ok">

	<table align="center" frame="border" class="mainTable">
		<tr>
			<td>
				<h2><spring:message code="body.title"/></h2>
				<h3><spring:message code="body.page"/>: <b><spring:message code="hello.page"/></b></h3>
			</td>
		</tr>
		<tr>
			<td><spring:message code="hello.message"/>:	<input type="text" id="userName" name="userName" value="${command.userName}" size="10"/></td>
		</tr>
		<tr>
			<td><input type="submit" id="ok" name="ok" value="<spring:message code="hello.button.ok"/>"/></td>
		</tr>		
	</table>
	
</form>
</body>


</html>