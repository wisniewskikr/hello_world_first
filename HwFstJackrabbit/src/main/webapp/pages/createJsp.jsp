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

	<div class="page">
		<div id="title" class="title"><h2>Hello World</h2></div>
		<div id="subtitle" class="subtitle"><h3>Page: <b>Create</b></h3></div>
		<div class="content">
			<div class="contentElement">
				<div class="text">Name * <form:errors id="errorName" path="name" cssClass="error" /> </div>
				<div class="input"><input type="text" id="name" name="name" /></div>
				<div class="description">Type your name here</div>
			</div>
			
		</div>
		<div class="buttons">
			<input type="button" id="create" name="create" value="Create" onclick="send('create/create-button');" title="Create user"/>
			<input type="button" id="back" name="back" value="Back" onclick="send('create/back-button');" title="Go back to list of users"/>
		</div>
	</div>		

</form:form>
</body>


</html>