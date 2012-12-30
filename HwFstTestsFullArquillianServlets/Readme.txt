INTEGRATION TESTS


1. Add user to Tomcat
In file <tomcat_home>/conf/tomcat-user.xml add user with role manager-gui:

<user username='admin' password='admin' roles='manager-gui,admin-gui,manager-script'/>


2. Run Tomcat


3. Update Maven properties (optional)


4. Run deploying and tests from Maven

mvn clean install -Pdev,unit,intg,deploy


-----------------------------------------------------

Integration tests profiles:
- WHERE TO DO: 
	* dev (developer machine), 
	* jen (jenkins machine), 
	* prod (production machite)
- WHAT TO DO: 
	* unit (run unit tests), 
	* intg (run integration tests)

For instance following command deploys 
application and runs unit and integration test
on developer machine:

mvn clean install -Pdev,unit,intg,deploy

or 

mvn clean install -Pjen,unit,intg,deploy