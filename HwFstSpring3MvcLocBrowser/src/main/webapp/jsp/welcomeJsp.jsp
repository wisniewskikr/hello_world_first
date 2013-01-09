<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>	

<html>


<head>
	<title><spring:message code="head.title"/></title>
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
				<h2><spring:message code="body.title"/></h2>
				<h3><spring:message code="body.page"/>: <b><spring:message code="welcome.page"/></b></h3>
			</td>
		</tr>		
		<tr>
			<td>
				<spring:message code="welcome.message"/>: <b>${command.userName} </b>
			</td>
		</tr>
		<tr>
			<td><input type="submit" id="back" name="back" value="<spring:message code="welcome.button.back"/>"/></td>
		</tr>
	</table>

</form>
</body>


</html>