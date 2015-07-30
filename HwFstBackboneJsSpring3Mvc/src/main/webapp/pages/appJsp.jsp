<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>	

<html>


<head>
	<title>Hello World</title>
	<base href="${pageContext.request.contextPath}/">
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/jquery-ui-1.10.3/smoothness/jquery-ui-1.10.3.custom.css">	
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script>
	<!-- script type="text/javascript" src="js/script.js"></script -->	
	<!-- Backbone scripts -->
	<script type="text/javascript" src="js/underscore-min-1.8.3.js"></script>
  	<script type="text/javascript" src="js/backbone-min-1.2.1.js"></script> 
</head>


<body>
<spring:form id="inputForm" commandName="command">

	<div class="page">
		<div id="title" class="title"><h2>Hello World</h2></div>
		
		<div id="container">Loading...</div>
		
	</div>		

</spring:form>

<!-- Templates -->
<script type="text/template" id="inputContent">
    <div id="subtitle" class="subtitle"><h3>Page: <b>Input</b></h3></div>
		<div id="content" class="content">
		<div class="contentElement">
			<div class="text">Name * <span id="errors" class="error"></span> </div>
			<div class="input"><input type="text" id="name" name="name" value="${name}" /></div>
			<div class="description">Type your name here</div>
		</div>
	</div>
	<div id="buttons" class="buttons">
		<input type="button" id="ok" name="ok" value="OK" title="Go to next page"/>
	</div>
</script> 

<script type="text/template" id="outputContent">
    <div id="subtitle" class="subtitle"><h3>Page: <b>Output</b></h3></div>
	<div id="content" class="content">
		<div class="contentElement">
		<div>&nbsp;</div>
		<div id="name" class="text"> Hello World <b><span id="result"></span></b> </div>
			<div class="image"><img id="duke" alt="duke" src="images/duke.jpg"></div>
		</div>
	</div>
	<div id="buttons" class="buttons">
		<input type="button" id="back" name="back" value="Back" title="Go back to previous page"/>
	</div>
</script> 

  <!-- =============== -->
  <!-- Javascript code -->
  <!-- =============== -->
  <script type="text/javascript">
  
	// Add this to avoid JSP marks in backbone template
	// and compilation errors
  _.templateSettings = {
	    interpolate: /\{\{(.+?)\}\}/gim,
	    evaluate: /\{\{(.+?)\}\}/gim,
	    escape: /\{\{\-(.+?)\}\}/gim
	};
  
    // View
	var AppView = Backbone.View.extend({
    	
      el: '#container',
      
      template: _.template($('#inputContent').html()),

      initialize: function(){
        this.render();
      },

      render: function(){
        this.$el.html(this.template());
      }
    });

    var appView = new AppView();
  </script>

</body>


</html>