DESCRIPTION
===========

This is example project with usage framework AngularJs with framework Spring 3 MVC. 
AngularJs is one of the java script frameworks (like BackboneJs or Ember) which can be used to create web application.
Here json object is sent as request and as response. 
To use AngularJs you have to:
- add js file with AngularJs:
<script type="text/javascript" src="js/angular.min.js"></script>
- on page define application and controller names:
<html ng-app="myApp" ng-controller="myCtrl">


Recommended deployment of project:
----------------------------------
Maven command:
mvn clean install -Ploc,deploy 

Recommended usage of project:
-----------------------------
Browser url:
http://localhost:8080/HwFstAnglarJsSpring3Mvc





PRECONDITIONS
=============

1. TOOLS
--------
This example project requires:
- Java (tested for version 1.7.0_10);
- Maven (tested for version 3.0.4);
- Tomcat (tested for version 7.0.34).

2. CONFIGURATION
----------------
All flexible configuration of project (server`s urls, logins, passwords etc.) can be changed in file:
<project_home>/project.properties





DEPLOYMENT
==========

You can deploy this application in two ways:
1. Copy war file
2. Use Maven plugin for deployment


Ad 1\ Copy war file
===================
You can do it using following steps:
- Open console;
- Go to project folder "HwFstAnglarJsSpring3Mvc;
- Use Maven command for building project. Command: 
  
  mvn clean install
  
- Copy file "HwFstAnglarJsSpring3Mvc.war" from <project_home>/target to <tomcat_home>/webapp


Ad 2\ Use Maven plugin for deployment
=====================================  
You can do it using following steps:
- Go to <tomcat_home>/conf/tomcat-users.xml and add user in manager role. For instance:

  <user username='admin' password='admin' roles='manager-gui,admin-gui,manager-script'/>
  
- Set server informations in file: <project_home>/project.properties. You have to set:
  loc.server.url, loc.server.username, loc.server.password;  
- Open console;
- Go to project folder "HwFstAjaxPostSpring3Mvc";
- Use Maven command for building and deployment project. Command:
 
  mvn clean install -Ploc,deploy  





USAGE
=====

Type in browser:

http://localhost:8080/HwFstAnglarJsSpring3Mvc