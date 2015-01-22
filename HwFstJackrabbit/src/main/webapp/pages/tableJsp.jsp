<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>	
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

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
<form:form modelAttribute="command">

	<div class="page">
		<div id="title" class="title"><h2>Hello World</h2></div>
		<div id="subtitle" class="subtitle"><h3>Page: <b>Table</b></h3></div>
		<div class="content">
		
			<div class="errorBlock">
				<form:errors id="errorMessage" path="*" cssClass="errorMessage" element="div" />
			</div>
			
			<div class="listHeader">
				<ul>
					<li class="listHeaderTitle">User List</li>
				</ul>
			</div>
		
			<div class="listActions">
				<ul>
					<li>
						<a href="javascript:sendWithModel('table/handle-table-action/create', 'command');" id="create">Create</a>
					</li>
					<li>
						<a href="javascript:sendWithModel('table/handle-table-action/view', 'command');" id="view">View</a>
					</li>
					<li>
						<a href="javascript:sendWithModel('table/handle-table-action/edit', 'command');" id="edit">Edit</a>
					</li>
					<li>
						<a href="javascript:sendWithModel('table/handle-table-action/delete', 'command');" id="delete">Delete</a>
					</li>
				</ul>		
			</div>
			
			<div class="listItems">
				<c:choose>
					<c:when test="${command.users == null || 
									empty command.users}">
						<div id="noData">No Data</div>			
					</c:when>
					<c:otherwise>
						<form:checkboxes element="div" items="${command.users}" itemLabel="name" itemValue="id" path="selectedUsersIds"/>
					</c:otherwise>
				</c:choose>	
			</div>
			
			
		</div>
		
	</div>		

</form:form>
</body>
</html>