INTEGRATION TESTS


Integration tests profiles:
- WHERE TO DO: 
	* dev (developer machine), 
	* jen (jenkins machine), 
	* prod (production machite)
- WHAT TO DO: 
	* unit (run unit tests), 
	* deploy (deploy application), 
	* intg (run integration tests)
	
You can run maven command with one where-to-do 
and few what-to-do.

For instance following command deploys 
application and runs unit and integration test
on developer machine:

mvn clean install -Pdev,unit,deploy,intg