<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>	

<html>


<head>
	<title>Hello World</title>
	<base href="${pageContext.request.contextPath}/">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.3/smoothness/jquery-ui-1.10.3.custom.css">
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript" src="js/script.js"></script>	
</head>


<body>
<form:form id="form" name="form" method="post" commandName="command">
<input type="hidden" id="id" name="id" value="${command.id}"/>

	<div class="page">
		<div id="title" class="title"><h2>Hello World</h2></div>
		<div id="subtitle" class="subtitle"><h3>Page: <b>Edit</b></h3></div>
		<div class="content">
			<div class="contentElement">
				<div class="text">Selected name is: <form:errors path="name" cssClass="error" /> </div>
				<div class="input"><input type="text" id="name" name="name" value="${command.name}"/></div>
				<div class="description">You can edit name here</div>
			</div>
			
		</div>
		<div class="buttons">
			<input type="button" id="update" name="update" value="Update" onclick="send('edit/update-button');" title="Update name"/>
			<input type="button" id="back" name="back" value="Back" onclick="send('edit/back-button');" title="Go back to list of users"/>
		</div>
	</div>		

</form:form>
</body>


</html>