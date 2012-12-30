INTEGRATION TESTS


1. Run deploying and tests from Maven

mvn clean install -Punit,intg


-----------------------------------------------------

Integration tests profiles:
- WHAT TO DO: 
	* unit (run unit tests), 
	* intg (run integration tests)

For instance following command deploys 
application and runs unit and integration test
on developer machine:

mvn clean install -Punit,intg